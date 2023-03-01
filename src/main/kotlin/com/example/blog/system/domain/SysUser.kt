package com.example.blog.system.domain

import com.example.blog.framework.web.domain.BaseEntity

class SysUser(
    /**
     * 用户ID
     */
    var userId: Long?,
    /**
     * 部门ID
     */
    var deptId: Long?,
    /**
     * 用户账号
     */
    var userName: String?,
    /**
     * 用户昵称
     */
    var nickName: String?,
    /**
     * 用户邮箱
     */
    var email: String?,
    /**
     * 手机号码
     */
    var phoneNumber: String?,
    /**
     * 用户性别
     */
    var sex: Int?,
    /**
     * 密码
     */
    var password: String?,
    /**
     * 账号状态（0：正常 1：停用）
     */
    var status: Int?,
    /**
     * 删除标志（0：代表存在 2：代表删除）
     */
    var delFlag: Int?,
    /**
     * 最后登录IP
     */
    var loginIp: String?,
    /**
     * 部门对象
     */
    var dept: SysDept?,
    /**
     * 角色对象
     */
    var roles: List<SysRole>?,
    /**
     * 角色组
     */
    var roleIds: Array<Long>?,
    /**
     * 岗位组
     */
    var postIds: Array<Long>?,
    /**
     * 角色ID
     */
    var roleId: Long?,
    /**
     * 是否是超级管理员
     */
    val isAdmin: Boolean = userId != null && userId == 1L
) : BaseEntity() {
    override fun toString(): String {
        return "SysUser(userId=$userId, deptId=$deptId, userName=$userName, nickName=$nickName, email=$email, phoneNumber=$phoneNumber, sex=$sex, password=$password, status=$status, delFlag=$delFlag, loginIp=$loginIp, dept=$dept, roles=$roles, roleIds=${roleIds?.contentToString()}, postIds=${postIds?.contentToString()}, roleId=$roleId, isAdmin=$isAdmin)"
    }
}
