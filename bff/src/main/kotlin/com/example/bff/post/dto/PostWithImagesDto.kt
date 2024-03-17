package com.example.bff.post.dto

import com.example.bff.image.ImageDto

data class PostWithImagesDto(
    val id: Long?,
    var userId: Long?,
    val title: String,
    val description: String,
    var images: List<ImageDto>? = emptyList()
)