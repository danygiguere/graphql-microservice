package com.example.bff.user

import com.example.bff.user.dto.UserDto
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitBodyOrNull


@RestController
class UserController(private val env: Environment? = null) {

    @GetMapping("/status/check")
    fun statusCheck(): String {
        return "Users api is working on port " + env?.getProperty("local.server.port")
    }

    @GetMapping("/users-service/status/check")
    suspend fun getUsersServiceStatusCheck(): ResponseEntity<String> {
        try {
            val webClient = WebClient.create("http://localhost:8012")
            val response = webClient.get()
                .uri("/status/check")
                .retrieve()
                .awaitBody<String>()
            return ResponseEntity.ok(response)
        } catch(e: Exception) {
            return ResponseEntity.internalServerError().build()
        }
    }

    @GetMapping("/users/{id}")
    suspend fun getById(@PathVariable id: Long): ResponseEntity<UserDto> {
        val webClient = WebClient.create("http://localhost:8012")
        try {
            val response = webClient.get()
                .uri("/users/$id")
                .retrieve()
                .awaitBodyOrNull<UserDto>()
            return if (response != null) ResponseEntity.ok(response)
            else ResponseEntity.notFound().build()
        } catch(e: Exception) {
            return ResponseEntity.internalServerError().build()
        }
    }

}