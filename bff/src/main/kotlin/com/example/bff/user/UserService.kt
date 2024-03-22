package com.example.bff.user

import com.example.bff.user.dto.LoginPayload
import com.example.bff.user.dto.RegisterPayload
import com.example.bff.user.dto.UserDto
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull

@Service
class UserService {

    val webClient = WebClient.create("http://localhost:8012")

    suspend fun getAll(): List<UserDto>? {
        return webClient.get()
            .uri("/users")
            .retrieve()
            .awaitBodyOrNull<List<UserDto>>()
    }

    suspend fun getById(id: Long): UserDto? {
        return webClient.get()
            .uri("/users/$id")
            .retrieve()
            .awaitBodyOrNull<UserDto>()
    }

    suspend fun register(registerPayload: RegisterPayload): String? {
        return webClient.post()
            .uri("/register")
            .bodyValue(registerPayload)
            .retrieve()
            .awaitBodyOrNull<String>()
    }

    suspend fun login(loginPayload: LoginPayload): UserDto? {
        return webClient.post()
            .uri("/login")
            .bodyValue(loginPayload)
            .retrieve()
            .awaitBodyOrNull<UserDto>()
    }



}