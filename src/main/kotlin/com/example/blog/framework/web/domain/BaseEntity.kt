package com.example.blog.framework.web.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.Instant

/**
 * Entity基类
 * @author weifengze
 */
open class BaseEntity(
    // 创建者
    val createBy: String? = null,
    /**
     * 创建时间
     * @link https://blog.csdn.net/aihe1907/article/details/101198745
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    val createTime: Instant,
    // 更新者
    val updateBy: String? = null,
    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updateTime: Instant,
    // 备注
    val remark: String? = null,
    // 请求参数
    var params: MutableMap<String, Any?> = HashMap()
) : Serializable {
    constructor() : this(null, Instant.now(), null, Instant.now(), null, HashMap());
}
