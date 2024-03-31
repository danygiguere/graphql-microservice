package com.example.bff.post

import com.example.bff.post.dto.PostDto
import com.example.bff.post.dto.PostWithImagesDto
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitBodyOrNull

@Service
class PostService {

    val webClient = WebClient.create("http://localhost:8013")

    suspend fun getAll(authorization: String?): List<PostDto>? {
        return webClient.get()
            .uri("/posts")
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .retrieve()
            .awaitBodyOrNull<List<PostDto>>()
    }

    suspend fun getAllWithImages(authorization: String?): List<PostWithImagesDto>? {
        return webClient.get()
            .uri("/posts-with-images")
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .retrieve()
            .awaitBodyOrNull<List<PostWithImagesDto>>()
    }


    suspend fun getById(id: Long, authorization: String?): PostDto? {
        return webClient.get()
            .uri("/posts/$id")
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .retrieve()
            .awaitBodyOrNull<PostDto>()
    }

    suspend fun getByIdWithImages(id: Long, authorization: String?): PostWithImagesDto? {
        return webClient.get()
            .uri("/posts/$id/with-images")
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .retrieve()
            .awaitBodyOrNull<PostWithImagesDto>()
    }

    suspend fun getByUserId(id: Long, authorization: String?): List<PostDto> {
        return webClient.get()
            .uri("/posts/for-user-id/$id")
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .retrieve()
            .awaitBody<List<PostDto>>()
    }

    suspend fun create(postDto: PostDto, authorization: String?): PostDto? {
        return webClient.post()
            .uri("/posts")
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .bodyValue(postDto)
            .retrieve()
            .awaitBodyOrNull<PostDto>()
    }

    suspend fun update(postDto: PostDto, authorization: String?): Long {
        return webClient.put()
            .uri("/posts/${postDto.id}")
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .bodyValue(postDto)
            .retrieve()
            .awaitBody<Long>()
    }

    suspend fun delete(id: Long, authorization: String?): Long {
        return webClient.delete()
            .uri("/posts/$id")
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .retrieve()
            .awaitBody<Long>()
    }
}