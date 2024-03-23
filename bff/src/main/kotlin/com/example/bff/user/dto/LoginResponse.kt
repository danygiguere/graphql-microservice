package com.example.bff.user.dto

data class LoginResponse(
    val user: UserDto?,
    val authToken: String?
)