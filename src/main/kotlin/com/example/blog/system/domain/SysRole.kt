package com.example.blog.system.domain

import com.example.blog.framework.web.domain.BaseEntity

class SysRole(
    /**
     * 角色ID
     */
    var roleId: Long?,
    /**
     * 角色名称
     */
    var roleName: String?,
    /**
     * 角色权限
     */
    var roleKey: String?,
    /**
     * 角色排序
     */
    var roleSort: Int?,
    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
     */
    var dataScope: Int?,
    /**
     * 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）
     */
    var isMenuCheckStrictly: Boolean = false,
    /**
     * 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）
     */
    var isDeptCheckStrictly: Boolean = false,
    /**
     *  角色状态（0正常 1停用）
     */
    var status: Int?,
    /**
     * 删除标志（0：代表存在 2：代表删除）
     */
    var delFlag: Int?,
    /**
     * 用户是否存在此角色标识 默认不存在
     */
    var isFlag: Boolean = false,
    /**
     * 菜单组
     */
    var menuIds: List<Long>?,
    /**
     * 部门组（数据权限）
     */
    var deptIds: List<Long>?,
    /**
     * 角色菜单权限
     */
    var permissions: Set<String>?,
    /**
     * 是否是超级管理员
     */
    val isAdmin: Boolean = roleId != null && roleId == 1L
) : BaseEntity() {
    override fun toString(): String {
        return "SysRole(roleId=$roleId, roleName=$roleName, roleKey=$roleKey, roleSort=$roleSort, dataScope=$dataScope, isMenuCheckStrictly=$isMenuCheckStrictly, isDeptCheckStrictly=$isDeptCheckStrictly, status=$status, delFlag=$delFlag, isFlag=$isFlag, menuIds=$menuIds, deptIds=$deptIds, permissions=$permissions, isAdmin=$isAdmin)"
    }
}
