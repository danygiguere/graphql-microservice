package com.example.bff.user.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class RegisterPayload(
    val email: String,
    @JsonIgnore()
    val password: String?,
    @JsonIgnore()
    val passwordConfirmation: String?
)