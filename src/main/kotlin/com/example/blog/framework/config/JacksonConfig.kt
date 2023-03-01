package com.example.blog.framework.config

import com.example.blog.common.utils.pareDate
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.ser.std.DateSerializer
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

/**
 *
 * Jackson 序列化/反序列化 Instant
 *
 * @author weifengze
 */
@Configuration
class JacksonConfig(
    @Value("\${spring.jackson.date-format}")
    private val formatter: String
) {

    @Bean
    fun format(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(formatter)
    }

    @Bean
    fun serializingObjectMapper(@Qualifier("format") dateTimeFormatter: DateTimeFormatter): ObjectMapper {
        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(dateTimeFormatter))
        javaTimeModule.addSerializer(Instant::class.java, InstantCustomSerializer(dateTimeFormatter))
        javaTimeModule.addSerializer(Date::class.java, DateSerializer(false, SimpleDateFormat(formatter)))
        javaTimeModule.addDeserializer(Instant::class.java, InstantCustomDeserializer())
        javaTimeModule.addDeserializer(Date::class.java, DateCustomDeserializer())
        return ObjectMapper()
            .registerModule(ParameterNamesModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(javaTimeModule)
    }
}

internal class InstantCustomSerializer(private val formatter: DateTimeFormatter) : JsonSerializer<Instant>() {

    override fun serialize(instant: Instant?, jsonGenerator: JsonGenerator?, serializerProvider: SerializerProvider?) {
        if (instant == null) {
            return
        }
        val jsonValue = formatter.format(instant.atZone(ZoneId.systemDefault()))
        jsonGenerator!!.writeString(jsonValue)
    }
}

internal class InstantCustomDeserializer : JsonDeserializer<Instant?>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Instant? {
        val dateString: String = p.text.trim()
        if (StringUtils.isNotBlank(dateString)) {
            val pareDate: Date?
            try {
                pareDate = pareDate(dateString)
                if (null != pareDate) {
                    return pareDate.toInstant()
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return null
    }
}

internal class DateCustomDeserializer : JsonDeserializer<Date?>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Date? {
        val dateString = p.text.trim()
        if (StringUtils.isNotBlank(dateString)) {
            try {
                return pareDate(dateString)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return null
    }
}
