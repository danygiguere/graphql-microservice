package com.example.postsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PostsServiceApplication

fun main(args: Array<String>) {
	runApplication<PostsServiceApplication>(*args)
}
