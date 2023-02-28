package com.example.blog

import com.example.blog.framework.web.domain.R
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class BlogKotlinWebApplication

fun main(args: Array<String>) {
    runApplication<BlogKotlinWebApplication>(*args)
}

@RestController
@RequestMapping("test")
class TestController {
    @GetMapping
    fun test(): R<String?> {
        return R.ok()
    }
}
