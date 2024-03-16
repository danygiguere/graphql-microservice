package com.example.bff.post

import com.example.bff.post.dto.InputPost
import com.example.bff.post.dto.PostDto
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated

@DgsComponent
@Validated
class PostMutation(private val postService: PostService) {
    @DgsData(parentType = "Mutation", field = "addPost")
    suspend fun addPost(@Valid @InputArgument("inputPost") inputPost: InputPost): PostDto? {
        val postDto = PostDto(id = 1, userId = 0, title = inputPost.title, description = inputPost.description)
        return postService.create(postDto)
    }
}

//@DgsComponent
//@Validated
//class PostMutation(private val postService: PostService)  {
//    @DgsData(parentType = "Mutation", field = "addPost")
//    suspend fun addPost(@Valid @InputArgument("inputPost") inputPost: InputPost): PostDto? {
//        val postDto = PostDto(id = 1, userId = 0, title = inputPost.title, description = inputPost.description)
//        return postService.create(postDto)
//    }
//}