package com.example.usersservice.user

import com.example.usersservice.user.dto.UserDto
import com.example.usersservice.user.dto.toUserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitOneOrNull
import org.springframework.r2dbc.core.bind
import org.springframework.r2dbc.core.flow
import org.springframework.stereotype.Repository

@Repository
class UserRepository(private val databaseClient: DatabaseClient,
                     private val userMapper: UserMapper,
) {

    suspend fun findAll(): Flow<UserDto>? =
        databaseClient.sql("SELECT * FROM users")
            .map(userMapper::apply)
            .flow()

    suspend fun findById(id: Long): UserDto? =
            databaseClient.sql("SELECT * FROM users WHERE id = :id")
                    .bind("id", id)
                    .map(userMapper::apply)
                    .awaitOneOrNull()

    suspend fun findByEmail(email: String): UserDto? =
        databaseClient.sql("SELECT * FROM users WHERE email = :email")
            .bind("email", email)
            .map(userMapper::apply)
            .awaitOneOrNull()

    suspend fun register(userDto: UserDto): UserDto =
        databaseClient.sql("INSERT INTO users (username, email, password) VALUES (:username, :email, :password)")
            .filter { statement, _ -> statement.returnGeneratedValues("id").execute() }
            .bind("username", userDto.username)
            .bind("email", userDto.email)
            .bind("password", userDto.password)
            .fetch()
            .first()
            .map { row ->
                val id = row["id"] as Long
                val userEntity = userDto.toUserEntity().copy(id = id)
                userEntity.toUserDto()
            }
            .awaitSingle()
}