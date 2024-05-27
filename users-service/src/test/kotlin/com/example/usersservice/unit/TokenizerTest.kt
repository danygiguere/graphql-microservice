package com.example.usersservice.unit

import com.auth0.jwt.interfaces.DecodedJWT
import com.example.usersservice.security.Tokenizer
import com.example.usersservice.user.UserRepository
import factory.UserFactory
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ContextConfiguration
@ExtendWith(MockitoExtension::class, SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TokenizerTest {

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
    fun `GIVEN id with JWT WHEN getById is called THEN a user is returned`() {
        runBlocking {
            // Given
            val userDto = userFactory.createOne()

            // When
            val bearerToken = tokenizer.tokenize(userDto.id.toString())

            // Then
            Assertions.assertNotNull(bearerToken)
        }
    }

    @Test
    fun `GIVEN token getById is called THEN a user is returned`() {
        runBlocking {
            // Given
            val userDto = userFactory.createOne()
            val bearerToken = tokenizer.tokenize(userDto.id.toString())

            // When
            val response: DecodedJWT = tokenizer.verify(bearerToken).awaitSingle()

            // Then
            Assertions.assertNotNull(response)
            Assertions.assertEquals(response.subject,  userDto.id.toString())
            Assertions.assertEquals(response.issuer,  "users-service")
        }
    }
}