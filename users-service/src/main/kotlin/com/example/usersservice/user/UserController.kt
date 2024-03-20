package com.example.usersservice.user

import com.example.usersservice.user.dto.RegisterPayload
import com.example.usersservice.user.dto.UserDto
import com.example.usersservice.user.dto.toUserDto
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import mu.KLogging
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(private val userService: UserService,
                     private val env: Environment? = null) {

    companion object: KLogging()

    @GetMapping("/status/check")
    fun statusCheck(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body("Users api is working on port " + env?.getProperty("local.server.port"))
    }

    @GetMapping("/users")
    suspend fun getAll(): ResponseEntity<Flow<UserDto>?> {
        val response = userService.findAll()
        return if (response != null) ResponseEntity.ok(response)
        else ResponseEntity.notFound().build()
    }

    @GetMapping("/users/{id}")
    suspend fun getById(@PathVariable id: Long): ResponseEntity<UserDto> {
        val response = userService.findById(id)
        return if (response != null) ResponseEntity.ok(response)
        else ResponseEntity.notFound().build()
    }

    @PostMapping("/register")
    suspend fun register(@Valid @RequestBody payload: RegisterPayload): ResponseEntity<String> {
        if (userService.findByEmail(payload.email) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.")
        }
        val newUser = userService.register(payload.toUserDto())
        return if (newUser != null) {
            ResponseEntity.ok().body("An email will be sent to you. Please confirm your email by clicking the verification link provided in the email")
        } else {
            logger.error("Error while registering user with email: ${payload.email}")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

}