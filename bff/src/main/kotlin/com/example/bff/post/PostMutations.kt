package com.example.bff.post

import com.example.bff.post.dto.InputPost
import com.example.bff.post.dto.PostDto
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated


@DgsComponent
class PostMutation(private val postService: PostService) {

    @DgsData(parentType = "Mutation", field = "addPost")
    suspend fun addPost(@InputArgument("inputPost") inputPost: InputPost): PostDto? {
        val postDto = PostDto(id = 1, userId = null, title = inputPost.title, description = inputPost.description)
        return postService.create(postDto)
    }

    @DgsData(parentType = "Mutation", field = "updatePost")
    suspend fun updatePost(@InputArgument("inputPost") inputPost: InputPost): Long? {
        val postDto = PostDto(id = inputPost.id, userId = null, title = inputPost.title, description = inputPost.description)
        return postService.update(postDto)
    }

    @DgsData(parentType = "Mutation", field = "deletePost")
    suspend fun deletePost(@InputArgument idFilter: Long): Long? {
        return postService.delete(idFilter)
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