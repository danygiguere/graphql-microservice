package com.example.usersservice.user

import com.example.usersservice.user.dto.UserDto
import io.r2dbc.spi.Row
import org.springframework.stereotype.Component
import java.util.function.BiFunction

@Component
class UserMapper: BiFunction<Row, Any, UserDto> {
    override fun apply(row: Row, o: Any): UserDto {
        return UserEntity(
                row.get("id") as Long,
                row.get("username") as String,
                row.get("email") as String,
                row.get("password") as String
        ).toUserDto()
    }
}