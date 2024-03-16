package com.example.bff.post.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class InputPost(
    val id: Long?,

    @get:NotNull()
    @get:NotEmpty()
    @get:Size(min = 6, max = 255, message = "{title.size}")
    var title: String,

    @get:NotEmpty()
    @get:Size(min = 6, max = 1000, message = "{description.size}")
    var description: String,
)