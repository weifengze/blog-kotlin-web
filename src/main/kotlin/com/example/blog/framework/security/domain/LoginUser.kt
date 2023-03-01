package com.example.blog.framework.security.domain

import com.example.blog.system.domain.SysUser
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * 登录用户身份权限
 *
 * @author weifengze
 */
class LoginUser(
    /**
     * 用户ID
     */
    var userId: Long?,
    /**
     * 部门ID
     */
    var deptId: Long?,
    /**
     * 用户唯一标识
     */
    var token: String?,
    /**
     * 登录时间
     */
    var loginTime: Long?,
    /**
     * 过期时间
     */
    var expireTime: Long?,
    /**
     * 登录IP地址
     */
    var ipaddr: String?,
    /**
     * 登录地点
     */
    var loginLocation: String?,
    /**
     * 浏览器类型
     */
    var browser: String?,
    /**
     * 操作系统
     */
    var os: String?,
    /**
     * 权限列表
     */
    var permissions: Set<String>?,
    /**
     * 用户信息
     */
    var user: SysUser?
) : UserDetails {
    override fun getAuthorities() = null

    @JsonIgnore
    override fun getPassword() = user!!.password!!

    override fun getUsername() = user!!.userName!!

    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    override fun isAccountNonExpired() = true

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     */
    @JsonIgnore
    override fun isAccountNonLocked() = true

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     */
    @JsonIgnore
    override fun isCredentialsNonExpired() = true

    /**
     * 是否可用 ,禁用的用户不能身份验证
     */
    @JsonIgnore
    override fun isEnabled() = true
}
