package com.example.bff.post

import com.example.bff.post.PostService
import com.example.bff.post.dto.PostDto
import com.example.bff.post.dto.PostWithImagesDto
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull


@DgsComponent
class PostsDataFetcher(private val postService: PostService) {

    @DgsData(parentType = "Query", field = "post")
    suspend fun post(@InputArgument idFilter: Int): PostDto? {
        return postService.findById(idFilter)
    }

    @DgsData(parentType = "Query", field = "postWithImages")
    suspend fun postWithImages(@InputArgument idFilter: Int): PostWithImagesDto? {
        return postService.findByIdWithImages(idFilter)
    }

    @DgsData(parentType = "Query", field = "posts")
    suspend fun posts(): List<PostDto>? {
        return postService.findAll()
    }


}