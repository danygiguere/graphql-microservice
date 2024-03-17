package com.example.bff.user

import com.example.bff.user.dto.LoginPayload
import com.example.bff.user.dto.RegisterPayload
import com.example.bff.user.dto.UserDto
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class UserMutations(private val userService: UserService) {

    @DgsData(parentType = "Mutation", field = "register")
    suspend fun register(@InputArgument("registerPayload") registerPayload: RegisterPayload): String? {
        return "not implemented yet"
//        return userService.register(registerPayload)
    }

    @DgsData(parentType = "Mutation", field = "login")
    suspend fun login(@InputArgument("loginPayload") loginPayload: LoginPayload): UserDto? {
        return userService.login(loginPayload)
    }

}