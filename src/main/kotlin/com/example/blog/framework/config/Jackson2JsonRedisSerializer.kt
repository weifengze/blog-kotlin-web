package com.example.blog.framework.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.data.redis.serializer.RedisSerializer

/**
 * Redis 使用 Jackson 序列化
 * @author weifengze
 */
class Jackson2JsonRedisSerializer<T>(
    private var clazz: Class<T>
) : RedisSerializer<T> {
    companion object {
        val objectMapper: ObjectMapper = ObjectMapper().registerModule(JavaTimeModule())
    }

    override fun serialize(t: T?): ByteArray {
        if (t == null) {
            return ByteArray(0)
        }
        return objectMapper.writeValueAsBytes(t)
    }

    override fun deserialize(bytes: ByteArray?): T? {
        if (bytes == null || bytes.isEmpty()) {
            return null
        }
        return objectMapper.readValue(bytes, clazz)
    }
}
