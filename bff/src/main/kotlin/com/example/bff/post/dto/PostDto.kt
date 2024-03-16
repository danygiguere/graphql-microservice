package com.example.bff.post.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class PostDto(
    val id: Long?,

    var userId: Long,

    @get:NotNull()
    @get:NotEmpty()
    @get:Size(min = 6, max = 255, message = "{title.size}")
    var title: String,

    @get:NotEmpty()
    @get:Size(min = 6, max = 1000, message = "{description.size}")
    var description: String,
)

//fun PostDto.toPostWithImagesDto(): PostWithImagesDto = PostWithImagesDto(
//        id = id,
//        userId = userId,
//        title = title,
//        description = description
//)