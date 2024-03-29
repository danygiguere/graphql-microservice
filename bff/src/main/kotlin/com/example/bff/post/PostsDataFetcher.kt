package com.example.bff.post

import com.example.bff.post.dto.PostDto
import com.example.bff.post.dto.PostWithImagesDto
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import org.springframework.http.HttpHeaders
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestHeader


@DgsComponent
class PostsDataFetcher(private val postService: PostService) {

    @DgsData(parentType = "Query", field = "posts")
    suspend fun posts(@RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?): List<PostDto>? {
        return postService.getAll(authorization)
    }

    @DgsData(parentType = "Query", field = "post")
    suspend fun post(@InputArgument idFilter: Long, @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?): PostDto? {
        return postService.getById(idFilter, authorization)
    }

    @DgsData(parentType = "Query", field = "postWithImages")
    suspend fun postWithImages(@InputArgument idFilter: Long, @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?): PostWithImagesDto? {
        return postService.getByIdWithImages(idFilter, authorization)
    }

}