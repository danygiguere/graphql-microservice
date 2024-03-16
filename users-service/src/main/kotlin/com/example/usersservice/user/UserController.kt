package com.example.usersservice.user

import com.example.usersservice.user.dto.UserDto
import kotlinx.coroutines.flow.Flow
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService,
                     private val env: Environment? = null) {

    @GetMapping("/status/check")
    fun statusCheck(): String {
        return "Users api is working on port " + env?.getProperty("local.server.port")
    }

    @GetMapping("/users")
    suspend fun getAll(): Flow<UserDto>? {
        return userService.findAll()
    }

    @GetMapping("/users/{id}")
    suspend fun getById(@PathVariable id: Long): UserDto? {
        return userService.findById(id)
    }

}