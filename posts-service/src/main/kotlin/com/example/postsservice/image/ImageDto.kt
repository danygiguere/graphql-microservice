package com.example.postsservice.image

data class ImageDto(
    val id: Long?,

    var postId: Long,

    val url: String
)

fun ImageDto.toEntity(): ImageEntity = ImageEntity(
    postId = postId,
    url = url
)