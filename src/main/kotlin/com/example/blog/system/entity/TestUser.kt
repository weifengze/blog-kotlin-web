package com.example.blog.system.entity

import com.example.blog.framework.web.domain.BaseEntity

class TestUser(
    val userName: String?,
    val password: String?,
    val email: String?
) : BaseEntity() {
}
