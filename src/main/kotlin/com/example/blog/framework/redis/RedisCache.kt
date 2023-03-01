package com.example.blog.framework.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisCache(
    @Autowired val redisTemplate: RedisTemplate<Any, Any>
) {

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    fun <T> setCacheObject(key: String, value: T) {
        redisTemplate.opsForValue().set(key, value!!)
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    fun <T> setCacheObject(key: String, value: T, timeout: Int, timeUnit: TimeUnit) {
        redisTemplate.opsForValue().set(key, value!!, timeout.toLong(), timeUnit)
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    fun expire(key: String, timeout: Long, unit: TimeUnit = TimeUnit.SECONDS): Boolean {
        return redisTemplate.expire(key, timeout, unit)
    }

    /**
     * 获取有效时间
     *
     * @param key Redis键
     * @return 有效时间
     */
    fun getExpire(key: String): Long {
        return redisTemplate.getExpire(key)
    }

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    fun hasKey(key: String): Boolean {
        return redisTemplate.hasKey(key)
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    fun getCacheObject(key: String): Any? {
        return redisTemplate.opsForValue()[key]
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    fun deleteObject(key: String): Boolean {
        return redisTemplate.delete(key)
    }
}


