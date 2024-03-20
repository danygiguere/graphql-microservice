package com.example.bff.user.dto

import com.example.bff.post.dto.PostDto

data class UserWithPostsDto(
        val id: Long?,
        val username: String,
        val email: String,
        var posts: List<PostDto>? = emptyList()
)
