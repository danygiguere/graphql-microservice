package com.example.bff.user.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class UserDto(
        val id: Long,
        val username: String,
        val email: String,
        @JsonIgnore()
        val password: String?
)

fun UserDto.toUserWithPostsDto(): UserWithPostsDto = UserWithPostsDto(
        id = id,
        username = username,
        email = email
)
