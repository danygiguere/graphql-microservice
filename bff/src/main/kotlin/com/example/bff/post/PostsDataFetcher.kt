package com.example.bff.post

import com.example.bff.post.dto.PostDto
import com.example.bff.post.dto.PostWithImagesDto
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument


@DgsComponent
class PostsDataFetcher(private val postService: PostService) {

    @DgsData(parentType = "Query", field = "posts")
    suspend fun posts(): List<PostDto>? {
        return postService.findAll()
    }

    @DgsData(parentType = "Query", field = "post")
    suspend fun post(@InputArgument idFilter: Long): PostDto? {
        return postService.findById(idFilter)
    }

    @DgsData(parentType = "Query", field = "postWithImages")
    suspend fun postWithImages(@InputArgument idFilter: Long): PostWithImagesDto? {
        return postService.findByIdWithImages(idFilter)
    }

}