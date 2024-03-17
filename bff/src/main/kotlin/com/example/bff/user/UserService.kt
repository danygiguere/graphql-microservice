package com.example.bff.user

import com.example.bff.user.dto.UserDto
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull

@Service
class UserService {

    val webClient = WebClient.create("http://localhost:8012")

    suspend fun findAll(): List<UserDto>? {
        return webClient.get()
            .uri("/users")
            .retrieve()
            .awaitBodyOrNull<List<UserDto>>()
    }

    suspend fun findById(id: Long): UserDto? {
        return webClient.get()
            .uri("/users/$id")
            .retrieve()
            .awaitBodyOrNull<UserDto>()
    }

}