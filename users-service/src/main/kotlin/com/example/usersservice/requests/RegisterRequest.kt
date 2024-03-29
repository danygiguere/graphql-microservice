package com.example.usersservice.requests

import com.example.usersservice.user.dto.UserDto
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class RegisterRequest(
    val id: Long?,

    @get:NotNull()
    @get:NotEmpty()
    @get:Size(min = 6, max = 25, message = "{username.size}")
    val username: String,

    @get:NotNull()
    @get:NotEmpty()
    @get:Email(message = "{email}")
    val email: String,

    @get:NotNull()
    @get:NotEmpty()
    @get:Size(min = 8, max = 25, message = "{password.size}")
    val password: String
)

fun RegisterRequest.toUserDto(): UserDto = UserDto(
    id = id,
    username = username,
    email = email,
    password = password
)