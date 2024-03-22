package com.example.bff.user.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class LoginPayload(
    val email: String,
    val password: String
)