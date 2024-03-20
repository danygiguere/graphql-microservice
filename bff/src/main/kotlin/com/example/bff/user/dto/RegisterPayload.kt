package com.example.bff.user.dto

data class RegisterPayload(
    val username: String,
    val email: String,
    val password: String?,
)