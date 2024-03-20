package com.example.bff.user

import com.example.bff.post.PostService
import com.example.bff.user.dto.UserDto
import com.example.bff.user.dto.UserWithPostsDto
import com.example.bff.user.dto.toUserWithPostsDto
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

@DgsComponent
class UsersDataFetcher(private val userService: UserService,
    private val postService: PostService) {

    @DgsData(parentType = "Query", field = "users")
    suspend fun users(): List<UserDto>? {
        return userService.getAll()
    }

    @DgsData(parentType = "Query", field = "user")
    suspend fun user(@InputArgument idFilter: Long): UserDto? {
        return userService.getById(idFilter)
    }

    @DgsData(parentType = "Query", field = "userWithPosts")
    suspend fun userWithPosts(@InputArgument idFilter: Long): UserWithPostsDto? = coroutineScope {
        val user = async{userService.getById(idFilter)}
        val posts = async{postService.getByUserId(idFilter)}
        return@coroutineScope user.await()?.toUserWithPostsDto()?.copy(posts = posts.await())
    }


}