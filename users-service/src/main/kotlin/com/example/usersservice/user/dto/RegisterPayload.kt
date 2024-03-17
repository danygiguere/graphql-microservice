package com.example.usersservice.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class RegisterPayload(
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

fun RegisterPayload.toUserDto(): UserDto = UserDto(
    id = id,
    username = username,
    email = email,
    password = password
)