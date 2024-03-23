package com.example.bff.user

import com.example.bff.user.dto.*
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class UserMutations(private val userService: UserService) {

    @DgsData(parentType = "Mutation", field = "register")
    suspend fun register(@InputArgument("registerPayload") registerPayload: RegisterPayload): String? {
        return userService.register(registerPayload)
    }

    @DgsData(parentType = "Mutation", field = "login")
    suspend fun login(@InputArgument("loginPayload") loginPayload: LoginPayload): LoginResponse {
        return userService.login(loginPayload)
    }
}