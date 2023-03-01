package com.example.blog.framework.config

import org.springframework.cache.annotation.CachingConfigurer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableCaching
class RedisConfig : CachingConfigurer {

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<Any, Any> {
        val template = RedisTemplate<Any, Any>()
        template.setConnectionFactory(connectionFactory)

        val serializer = Jackson2JsonRedisSerializer(Any::class.java)

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = serializer


        // Hash的key也采用StringRedisSerializer的序列化方式
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = serializer

        template.afterPropertiesSet()
        return template
    }

}
