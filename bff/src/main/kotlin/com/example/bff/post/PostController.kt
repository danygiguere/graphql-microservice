package com.example.bff.post

import com.example.bff.post.dto.PostDto
import com.example.bff.post.dto.PostWithImagesDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitBodyOrNull

@RestController
class PostController() {

    @GetMapping("/posts-service/status/check")
    suspend fun getUsersServiceStatusCheck(): String {
        val webClient = WebClient.create("http://localhost:8013")
        return webClient.get()
            .uri("/status/check")
            .retrieve()
            .awaitBody<String>()
    }

    @GetMapping("/posts")
    suspend fun getAll(): ResponseEntity<List<PostDto>> {
        try {
            val webClient = WebClient.create("http://localhost:8013")
            val response = webClient.get()
                .uri("/posts")
                .retrieve()
                .awaitBodyOrNull<List<PostDto>>()
            return if (response != null) ResponseEntity.ok(response)
            else ResponseEntity.notFound().build()
        } catch(e: Exception) {
            return ResponseEntity.internalServerError().build()
        }
    }

    @GetMapping("/posts/{id}")
    suspend fun getById(@PathVariable id: Long): ResponseEntity<PostDto> {
        val webClient = WebClient.create("http://localhost:8013")
        val response = webClient.get()
            .uri("/posts/$id")
            .retrieve()
            .awaitBodyOrNull<PostDto>()
        return if (response != null) ResponseEntity.ok(response)
        else ResponseEntity.notFound().build()
    }

    @GetMapping("/posts/{id}/with-images")
    suspend fun getByIdWithImages(@PathVariable id: Long): ResponseEntity<PostWithImagesDto> {
        try {
            val webClient = WebClient.create("http://localhost:8013")
            val response = webClient.get()
                .uri("/posts/$id/with-images")
                .retrieve()
                .awaitBodyOrNull<PostWithImagesDto>()
            return if (response != null) ResponseEntity.ok(response)
            else ResponseEntity.notFound().build()
        } catch(e: Exception) {
            return ResponseEntity.internalServerError().build()
        }
    }

    @PostMapping("/posts")
    suspend fun create(@Valid @RequestBody postDto: PostDto): ResponseEntity<PostDto> {
        try {
            val webClient = WebClient.create("http://localhost:8013")
            val response = webClient.post()
                .uri("/posts")
                .bodyValue(postDto)
                .retrieve()
                .awaitBodyOrNull<PostDto>()
            return if (response != null) ResponseEntity.ok(response)
            else ResponseEntity.notFound().build()
        } catch(e: Exception) {
            return ResponseEntity.internalServerError().build()
        }
    }

    @PutMapping("/posts/{id}")
    suspend fun update(@PathVariable id: Long, @Valid @RequestBody postDto: PostDto): ResponseEntity<Long> {
        try {
            val webClient = WebClient.create("http://localhost:8013")
            val response = webClient.put()
                .uri("/posts/$id")
                .bodyValue(postDto)
                .retrieve()
                .awaitBodyOrNull<Long>()
            return if (response != null) ResponseEntity.ok(response)
            else ResponseEntity.notFound().build()
        } catch(e: Exception) {
            return ResponseEntity.internalServerError().build()
        }
    }

    @DeleteMapping("/posts/{id}")
    suspend fun delete(@PathVariable id: Long): ResponseEntity<Long> {
        try {
            val webClient = WebClient.create("http://localhost:8013")
            val response = webClient.delete()
                .uri("/posts/$id")
                .retrieve()
                .awaitBodyOrNull<Long>()
            return if (response != null) ResponseEntity.ok(response)
            else ResponseEntity.notFound().build()
        } catch(e: Exception) {
            return ResponseEntity.internalServerError().build()
        }
    }
}