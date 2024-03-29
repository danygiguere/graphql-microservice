package com.example.bff.post

import com.example.bff.post.dto.InputPost
import com.example.bff.post.dto.PostDto
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import org.springframework.http.HttpHeaders
import org.springframework.security.access.prepost.PreAuthorize
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestHeader


@DgsComponent
class PostMutation(private val postService: PostService) {

    @PreAuthorize("hasRole('USER')")
    @DgsData(parentType = "Mutation", field = "addPost")
    suspend fun addPost(@InputArgument("inputPost") inputPost: InputPost,
                        @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?): PostDto? {
        val postDto = PostDto(id = 1, userId = null, title = inputPost.title, description = inputPost.description)
        return postService.create(postDto, authorization)
    }

    @PreAuthorize("hasRole('USER')")
    @DgsData(parentType = "Mutation", field = "updatePost")
    suspend fun updatePost(@InputArgument("inputPost") inputPost: InputPost,
                           @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?): Long? {
        val postDto = PostDto(id = inputPost.id, userId = null, title = inputPost.title, description = inputPost.description)
        return postService.update(postDto, authorization)
    }

    @PreAuthorize("hasRole('USER')")
    @DgsData(parentType = "Mutation", field = "deletePost")
    suspend fun deletePost(@InputArgument idFilter: Long,
                           @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?): Long? {
        return postService.delete(idFilter, authorization)
    }

}


/*
for validation in the bff using the @valid annotation, uncomment the lines below
 */
//@DgsComponent
//@Validated
//class PostMutation(private val postService: PostService)  {
//    @DgsData(parentType = "Mutation", field = "addPost")
//    suspend fun addPost(@Valid @InputArgument("inputPost") inputPost: InputPost): PostDto? {
//        val postDto = PostDto(id = 1, userId = 0, title = inputPost.title, description = inputPost.description)
//        return postService.create(postDto)
//    }
//}