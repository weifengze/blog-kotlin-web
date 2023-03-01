package com.example.blog.system.domain

import com.example.blog.framework.web.domain.BaseEntity

class TestUser(
    var userName: String?,
    var password: String?,
    var email: String?
) : BaseEntity() {
    override fun toString(): String {
        return "TestUser(userName=$userName, password=$password, email=$email)"
    }
}
