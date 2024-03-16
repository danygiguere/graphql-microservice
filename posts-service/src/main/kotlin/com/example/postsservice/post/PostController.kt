package com.example.postsservice.post

import com.example.postsservice.post.dto.PostDto
import com.example.postsservice.post.dto.PostWithImagesDto
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.*

@RestController
class PostController(private val postService: PostService,
                     private val env: Environment? = null) {

    @GetMapping("/status/check")
    fun statusCheck(): String {
        return "Posts api is working on port " + env?.getProperty("local.server.port")
    }

    @GetMapping("/posts")
    suspend fun getAll(): Flow<PostDto>? {
        return postService.findAll()
    }

    @GetMapping("/posts/{id}")
    suspend fun getById(@PathVariable id: Long): PostDto? {
        return postService.findById(id)
    }

    @GetMapping("/posts/{id}/with-images")
    suspend fun getByIdWithImages(@PathVariable id: Long): PostWithImagesDto? {
        return postService.findByIdWithImages(id)
    }

    @PostMapping("/posts")
    suspend fun create(@Valid @RequestBody postDto: PostDto): PostDto? {
        val userId: Long = 1; // for demo only. The userId needs to be taken from the auth user
        return postService.create(userId, postDto)
    }

    @PutMapping("/posts/{id}")
    suspend fun update(@PathVariable id: Long, @Valid @RequestBody postDto: PostDto): Long {
        return postService.update(id, postDto)
    }

    @DeleteMapping("/posts/{id}")
    suspend fun delete(@PathVariable id: Long): Long {
        return postService.delete(id)
    }
}