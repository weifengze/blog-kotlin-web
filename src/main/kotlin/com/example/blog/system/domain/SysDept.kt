package com.example.blog.system.domain

import com.example.blog.framework.web.domain.BaseEntity

class SysDept(
    /**
     * 部门ID
     */
    var deptId: Long?,

    /**
     * 父部门ID
     */
    var parentId: Long?,

    /**
     * 祖级列表
     */
    var ancestors: String?,

    /**
     * 部门名称
     */
    var deptName: String?,

    /**
     * 显示顺序
     */
    var orderNum: Int?,

    /**
     * 负责人
     */
    var leader: String?,

    /**
     * 联系电话
     */
    var phone: String?,

    /**
     * 邮箱
     */
    var email: String?,

    /**
     * 部门状态:0正常,1停用
     */
    var status: Int?,

    /**
     * 删除标志（0：代表存在 2：代表删除）
     */
    var delFlag: Int?,

    /**
     * 父部门名称
     */
    var parentName: String?,

    /**
     *  子部门
     */
    var children: List<SysDept> = ArrayList()
) : BaseEntity() {
    override fun toString(): String {
        return "SysDept(deptId=$deptId, parentId=$parentId, ancestors=$ancestors, deptName=$deptName, orderNum=$orderNum, leader=$leader, phone=$phone, email=$email, status=$status, delFlag=$delFlag, parentName=$parentName, children=$children)"
    }
}
