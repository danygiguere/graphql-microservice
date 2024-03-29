package com.example.postsservice.post.dto

import com.example.postsservice.image.ImageDto

data class PostWithImagesDto(
    val id: Long?,
    var userId: Long,
    val title: String,
    val description: String,
    var images: List<ImageDto>? = emptyList()
)