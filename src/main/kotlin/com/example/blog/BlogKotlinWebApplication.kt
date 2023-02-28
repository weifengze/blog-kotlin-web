package com.example.blog

import com.example.blog.framework.web.domain.R
import com.example.blog.system.entity.TestUser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class BlogKotlinWebApplication

fun main(args: Array<String>) {
    runApplication<BlogKotlinWebApplication>(*args)
}

@RestController
@RequestMapping("/test")
class TestController {
    companion object {
        private val jsonUtils = ObjectMapper()
    }

    @GetMapping
    fun test(user: TestUser?): R<TestUser?> {
        jsonUtils.registerModule(JavaTimeModule())
        println(jsonUtils.writeValueAsString(user))
        return R.ok(user)
    }
}
