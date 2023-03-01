package com.example.blog.framework.security.service

import com.example.blog.common.constants.TOKEN_PREFIX
import com.example.blog.framework.redis.RedisCache
import com.example.blog.framework.security.domain.LoginUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * 毫秒
 */
const val MILLIS_SECOND: Long = 1000

/**
 * 一分钟毫秒值
 */
const val MILLIS_MINUTE: Long = 60 * MILLIS_SECOND

/**
 * 二十分钟毫秒值
 */
private const val MILLIS_MINUTE_TEN: Long = 20 * MILLIS_MINUTE

@Component
class TokenService(
    /**
     * 令牌自定义标识
     */
    @Value("\${token.header}")
    private val header: String,
    /**
     * 令牌秘钥
     */
    @Value("\${token.secret}")
    private val secret: String,
    /**
     * 令牌有效期（默认30分钟）
     */
    @Value("\${token.expireTime}")
    private val expireTime: Long = 0,
    @Autowired val redisCache: RedisCache
) {

    fun getSecretKey() = Keys.hmacShaKeyFor(secret.toByteArray())!!

//    fun getLoginUser(request: HttpServletRequest): LoginUser {
//        val token = getToken(request)
//        token.isNotEmpty().let {
//            val claims: Claims = parseToken(token)
//        }
//        return LoginUser()
//    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     */
    private fun createToken(claims: Map<String, Any?>): String? {
        return Jwts.builder()
            .setClaims(claims)
            .signWith(getSecretKey())
            .compact()
    }


    fun parseToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getToken(request: HttpServletRequest): String {
        var token = request.getHeader(header)
        if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "")
        }
        return token
    }
}
