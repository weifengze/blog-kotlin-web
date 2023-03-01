package com.example.blog.utils

import com.example.blog.system.domain.TestUser
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import java.time.Instant
import java.util.concurrent.TimeUnit

@SpringBootTest
class RedisTemplateTest(
    @Autowired val redisTemplate: RedisTemplate<Any, Any>
) {
    @Test
    fun `test string write redis cache`() {
        redisTemplate.opsForValue().set("testing", "test write redis cache")
    }

    @Test
    fun `test object write redis cache`() {
        val testUser = TestUser("admin", "admin", "admin@hotmail.com").apply {
            this.createBy = "admin"
            this.createTime = Instant.now()
        }
        redisTemplate.opsForValue().set("testing", testUser, 10, TimeUnit.SECONDS)
        val cacheUser = redisTemplate.opsForValue().get("testing")
        println(cacheUser)
    }
}
