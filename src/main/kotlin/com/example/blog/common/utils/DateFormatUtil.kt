// 日期格式化
package com.example.blog.common.utils

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.time.FastDateFormat
import org.jetbrains.annotations.NotNull
import java.text.ParseException
import java.time.Instant
import java.util.*


const val SYMBOL_DOT = "\\."

//日期正则yyyy-MM
const val DATE_REGEX_YYYYMM = "^\\d{4}-\\d{1,2}$"

//日期正则yyyy-MM-dd
const val DATE_REGEX_YYYYMMDD = "^\\d{4}-\\d{1,2}-\\d{1,2}$"

//日期正则yyyy-MM-dd hh:mm
const val DATE_REGEX_YYYYMMDDHHMM = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}$"

//日期正则yyyy-MM-dd hh:mm:ss
const val DATE_REGEX_YYYYMMDDHHMMSS = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$"

//Instant日期秒+纳秒
const val DATE_REGEX_SECOND_DOT_NANOSECOND = "^[0-9]{1,}\\.[0-9]{1,9}$"

//日期正则yyyy-MM-dd'T'HH:mm:ssZ
const val DATE_REGEX_YYYYMMDD_T_HHMMSS_Z = "^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}Z$"

//日期正则yyyy-MM-dd'T'HH:mm:ss.SSSZ
const val DATE_REGEX_YYYYMMDD_T_HHMMSS_SSS_Z = "^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}Z$"


// 以T分隔日期和时间，并带时区信息，符合ISO8601规范
const val PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"
const val PATTERN_ISO_ON_SECOND = "yyyy-MM-dd'T'HH:mm:ssZZ"
const val PATTERN_ISO_ON_DATE = "yyyy-MM-dd"
const val PATTERN_ISO_ON_MONTH = "yyyy-MM"

// 以空格分隔日期和时间，不带时区信息
const val PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss.SSS"
const val PATTERN_DEFAULT_ON_SECOND = "yyyy-MM-dd HH:mm:ss"
const val PATTERN_DEFAULT_ON_MINUTE = "yyyy-MM-dd HH:mm"


// 使用工厂方法FastDateFormat.getInstance(), 从缓存中获取实例
// 以T分隔日期和时间，并带时区信息，符合ISO8601规范
val ISO_FORMAT: FastDateFormat = FastDateFormat.getInstance(PATTERN_ISO)
val ISO_ON_SECOND_FORMAT: FastDateFormat = FastDateFormat.getInstance(PATTERN_ISO_ON_SECOND)
val ISO_ON_DATE_FORMAT: FastDateFormat = FastDateFormat.getInstance(PATTERN_ISO_ON_DATE)
val ISO_ON_MONTH_FORMAT: FastDateFormat = FastDateFormat.getInstance(PATTERN_ISO_ON_MONTH)

// 以空格分隔日期和时间，不带时区信息
val DEFAULT_FORMAT: FastDateFormat = FastDateFormat.getInstance(PATTERN_DEFAULT)
val DEFAULT_ON_SECOND_FORMAT: FastDateFormat = FastDateFormat.getInstance(PATTERN_DEFAULT_ON_SECOND)
val DEFAULT_ON_MINUTE_FORMAT: FastDateFormat = FastDateFormat.getInstance(PATTERN_DEFAULT_ON_MINUTE)

/**
 * 将日期格式的字符串转换成指定格式的日期
 * @param pattern 日期格式
 * @param dateString 日期字符串
 * @return
 * @throws ParseException
 */
@Throws(ParseException::class)
fun pareDate(@NotNull pattern: String?, @NotNull dateString: String?): Date? {
    return FastDateFormat.getInstance(pattern).parse(dateString)
}

/**
 * 将日期格式的字符串根据正则转换成相应格式的日期
 * @param dateString 日期字符串
 * @return
 * @throws ParseException
 */
@Throws(ParseException::class)
fun pareDate(@NotNull dateString: String): Date? {
    val source = dateString.trim()
    return if (StringUtils.isNotBlank(source)) {
        if (source.matches(DATE_REGEX_YYYYMM.toRegex())) {
            ISO_ON_MONTH_FORMAT.parse(source)
        } else if (source.matches(DATE_REGEX_YYYYMMDD.toRegex())) {
            ISO_ON_DATE_FORMAT.parse(source)
        } else if (source.matches(DATE_REGEX_YYYYMMDDHHMM.toRegex())) {
            DEFAULT_ON_MINUTE_FORMAT.parse(source)
        } else if (source.matches(DATE_REGEX_YYYYMMDDHHMMSS.toRegex())) {
            DEFAULT_ON_SECOND_FORMAT.parse(source)
        } else if (source.matches(DATE_REGEX_YYYYMMDD_T_HHMMSS_Z.toRegex())) {
            ISO_ON_SECOND_FORMAT.parse(source)
        } else if (source.matches(DATE_REGEX_YYYYMMDD_T_HHMMSS_SSS_Z.toRegex())) {
            ISO_FORMAT.parse(source)
        } else if (source.matches(DATE_REGEX_SECOND_DOT_NANOSECOND.toRegex())) {
            val split = source.split(SYMBOL_DOT.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            Date.from(Instant.ofEpochSecond(split[0].toLong(), split[1].toLong()))
        } else {
            throw IllegalArgumentException("Invalid date value '$source'")
        }
    } else null
}
