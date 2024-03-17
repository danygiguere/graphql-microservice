package com.example.bff.user

import com.example.bff.user.dto.UserDto
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class UsersDataFetcher(private val userService: UserService) {

    @DgsData(parentType = "Query", field = "users")
    suspend fun users(): List<UserDto>? {
        return userService.findAll()
    }

    @DgsData(parentType = "Query", field = "user")
    suspend fun user(@InputArgument idFilter: Long): UserDto? {
        return userService.findById(idFilter)
    }

}