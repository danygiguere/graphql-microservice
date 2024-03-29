package com.example.bff.post

import com.example.bff.post.dto.PostDto
import com.example.bff.post.dto.PostWithImagesDto
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitBodyOrNull

@Service
class PostService {

    val webClient = WebClient.create("http://localhost:8013")

    suspend fun getAll(): List<PostDto>? {
        return webClient.get()
            .uri("/posts")
            .retrieve()
            .awaitBodyOrNull<List<PostDto>>()
    }

    suspend fun getById(id: Long): PostDto {
        return webClient.get()
            .uri("/posts/$id")
            .retrieve()
            .awaitBody<PostDto>()
    }

    suspend fun getByIdWithImages(id: Long): PostWithImagesDto? {
        return webClient.get()
            .uri("/posts/$id/with-images")
            .retrieve()
            .awaitBodyOrNull<PostWithImagesDto>()
    }

    suspend fun getByUserId(id: Long): List<PostDto> {
        return webClient.get()
            .uri("/users/$id/posts")
            .retrieve()
            .awaitBody<List<PostDto>>()
    }

    suspend fun create(postDto: PostDto): PostDto? {
        return webClient.post()
            .uri("/posts")
            .bodyValue(postDto)
            .retrieve()
            .awaitBodyOrNull<PostDto>()
    }

    suspend fun update(postDto: PostDto): Long {
        return webClient.put()
            .uri("/posts/${postDto.id}")
            .bodyValue(postDto)
            .retrieve()
            .awaitBody<Long>()
    }

    suspend fun delete(id: Long): Long {
        return webClient.delete()
            .uri("/posts/$id")
            .retrieve()
            .awaitBody<Long>()
    }
}