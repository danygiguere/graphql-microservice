package com.example.postsservice.post

import com.example.postsservice.image.ImageRepository
import com.example.postsservice.post.dto.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import org.springframework.stereotype.Service

@Service
class PostService(val postRepository: PostRepository,
                  private val imageRepository: ImageRepository) {

    suspend fun findAll(): Flow<PostDto>? =
            postRepository.findAll()

    suspend fun findAllWithImages(): Flow<PostWithImagesDto>? = coroutineScope {
        val posts = async{findAll()}
        val images = async{imageRepository.findAll()}

        posts.await()?.map { post ->
            val postImages = images.await()?.filter { it.postId == post.id }?.toList()
            PostWithImagesDto(post.id, post.userId, post.title, post.description, postImages)
        }
    }

    suspend fun findById(id: Long): PostDto? =
            postRepository.findById(id)

    suspend fun findByUserId(userId: Long): Flow<PostDto>? =
        postRepository.findByUserId(userId)

    // oneToMany relationship query example
    suspend fun findByIdWithImages(id: Long): PostWithImagesDto? = coroutineScope {
        val post = async{findById(id)}
        val images = async{imageRepository.findByPostId(id)?.toList()}
        return@coroutineScope post.await()?.toPostWithImagesDto()?.copy(images = images.await())
    }

    suspend fun create(userId: Long, postDto: PostDto): PostDto? =
            postRepository.create(postDto.copy(userId = userId))

    suspend fun update(id: Long, postDto: PostDto): Long =
            postRepository.update(id, postDto)

    suspend fun delete(id: Long): Long =
            postRepository.delete(id)
}