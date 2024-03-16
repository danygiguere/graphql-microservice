package com.example.bff.post

import com.example.bff.post.dto.PostDto
import com.example.bff.post.dto.PostWithImagesDto
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull

@Service
class PostService {

    suspend fun findById(id: Int): PostDto? {
        val webClient = WebClient.create("http://localhost:8013")
        return webClient.get()
            .uri("/posts/$id")
            .retrieve()
            .awaitBodyOrNull<PostDto>()
    }

    suspend fun findByIdWithImages(id: Int): PostWithImagesDto? {
        val webClient = WebClient.create("http://localhost:8013")
        return webClient.get()
            .uri("/posts/$id/with-images")
            .retrieve()
            .awaitBodyOrNull<PostWithImagesDto>()
    }

    suspend fun findAll(): List<PostDto>? {
        val webClient = WebClient.create("http://localhost:8013")
        return webClient.get()
            .uri("/posts")
            .retrieve()
            .awaitBodyOrNull<List<PostDto>>()
    }

    suspend fun create(postDto: PostDto): PostDto? {
        val webClient = WebClient.create("http://localhost:8013")
        return webClient.post()
            .uri("/posts")
            .bodyValue(postDto)
            .retrieve()
            .awaitBodyOrNull<PostDto>()
    }

}