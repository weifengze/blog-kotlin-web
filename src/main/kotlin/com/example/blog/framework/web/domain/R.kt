package com.example.blog.framework.web.domain

import java.io.Serializable

class R<T> : Serializable {
    var code = 0
    var msg: String? = null
    var data: T? = null

    companion object {
        private const val serialVersionUID = 1L

        /**
         * 成功
         */
        private const val SUCCESS = 0

        /**
         * 失败
         */
        private const val FAIL = 500

        fun <T> ok(): R<T?> {
            return restResult(null, SUCCESS, "操作成功")
        }

        fun <T> ok(data: T): R<T> {
            return restResult(data, SUCCESS, "操作成功")
        }

        fun <T> ok(data: T, msg: String): R<T> {
            return restResult(data, SUCCESS, msg)
        }

        fun <T> fail(): R<T?> {
            return restResult(null, FAIL, "操作失败")
        }

        fun <T> fail(msg: String): R<T?> {
            return restResult(null, FAIL, msg)
        }

        fun <T> fail(data: T): R<T> {
            return restResult(data, FAIL, "操作失败")
        }

        fun <T> fail(data: T, msg: String): R<T> {
            return restResult(data, FAIL, msg)
        }

        fun <T> fail(code: Int, msg: String): R<T?> {
            return restResult(null, code, msg)
        }

        private fun <T> restResult(data: T, code: Int, msg: String): R<T> {
            return R<T>().apply {
                this.msg = msg
                this.code = code
                this.data = data
            }
        }
    }
}
