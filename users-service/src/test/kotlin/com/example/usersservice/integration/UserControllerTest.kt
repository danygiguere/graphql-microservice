package com.example.usersservice.integration

import com.example.usersservice.requests.LoginRequest
import com.example.usersservice.security.Tokenizer
import com.example.usersservice.user.UserRepository
import factory.UserFactory
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest(@Autowired val webTestClient: WebTestClient) {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var tokenizer: Tokenizer

    private lateinit var userFactory: UserFactory

    @BeforeEach
    fun setUp() {
        userFactory = UserFactory(userRepository)
    }

    @Test
    fun `GIVEN valid data WHEN login is called THEN an authorization header and a user are returned`() {
        runBlocking {
            // Given
            val userDto = UserFactory(userRepository).createOne()
            val loginRequest = userDto.password?.let { LoginRequest(userDto.email, it) }

            // When
            loginRequest?.let {
                webTestClient.post()
                    .uri("/login")
                    .bodyValue(it)
                    .exchange()
                    .expectStatus().is2xxSuccessful
                    .expectHeader().exists("Authorization")
                    .expectBody()
                    .jsonPath("$.id").isNotEmpty()
                    .jsonPath("$.username").isEqualTo(userDto.username)
                    .jsonPath("$.email").isEqualTo(userDto.email)
            }
        }
    }

    @Test
    fun `GIVEN id but no JWT WHEN getById is called THEN a 401 is returned`() {
        runBlocking {
            // Given
            val userDto = UserFactory(userRepository).createOne()

            // When
            webTestClient.post()
                .uri("/user/${userDto.id}")
                .exchange()
                .expectStatus().isEqualTo(401)
        }
    }

    @Test
    fun `GIVEN id with JWT WHEN getById is called THEN a user is returned`() {
        runBlocking {
            // Given
            val userDto = UserFactory(userRepository).createOne()
            val bearerToken = tokenizer.createBearerToken(userDto.id)

            // When
            webTestClient.get()
                .uri("/users/${userDto.id}")
                .header(HttpHeaders.AUTHORIZATION, bearerToken)
                .exchange()
                .expectStatus().is2xxSuccessful
        }
    }

}