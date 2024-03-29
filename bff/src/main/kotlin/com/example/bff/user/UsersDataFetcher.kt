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
import org.springframework.http.HttpHeaders
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestHeader

@DgsComponent
class UsersDataFetcher(private val userService: UserService,
    private val postService: PostService) {

    @DgsData(parentType = "Query", field = "users")
    suspend fun users(@RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?): List<UserDto>? {
        return userService.getAll(authorization)
    }

    @DgsData(parentType = "Query", field = "user")
    suspend fun user(@InputArgument idFilter: Long, @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?): UserDto? {
        return userService.getById(idFilter, authorization)
    }

    @DgsData(parentType = "Query", field = "userWithPosts")
    suspend fun userWithPosts(@InputArgument idFilter: Long, @RequestHeader(HttpHeaders.AUTHORIZATION) authorization: String?): UserWithPostsDto? = coroutineScope {
        val user = async{userService.getById(idFilter, authorization)}
        val posts = async{postService.getByUserId(idFilter, authorization)}
        return@coroutineScope user.await()?.toUserWithPostsDto()?.copy(posts = posts.await())
    }


}