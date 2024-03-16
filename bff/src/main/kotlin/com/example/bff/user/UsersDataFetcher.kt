package com.example.bff.user

import com.example.bff.user.dto.UserDto
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull


@DgsComponent
class UsersDataFetcher {

    @DgsData(parentType = "Query", field = "user")
    suspend fun user(@InputArgument idFilter: Int): UserDto? {
        val webClient = WebClient.create("http://localhost:8012")
        return webClient.get()
            .uri("/users/$idFilter")
            .retrieve()
            .awaitBodyOrNull<UserDto>()
    }

    @DgsData(parentType = "Query", field = "users")
    suspend fun users(): List<UserDto>? {
        val webClient = WebClient.create("http://localhost:8012")
        return webClient.get()
            .uri("/users")
            .retrieve()
            .awaitBodyOrNull<List<UserDto>>()
    }

}