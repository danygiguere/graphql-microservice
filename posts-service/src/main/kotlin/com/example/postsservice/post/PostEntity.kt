package com.example.postsservice.post
import com.example.postsservice.post.dto.PostDto
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class PostEntity(
    @Id var id: Long? = null,
    var userId: Long,
    val title: String,
    val description: String,
)

fun PostEntity.toDto(): PostDto = PostDto(
    id = id,
    userId = userId,
    title = title,
    description = description,
)