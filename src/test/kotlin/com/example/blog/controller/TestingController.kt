package com.example.blog.controller

import com.example.blog.TestController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcTest
class TestingController(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `Test Mockito`() {
        val builder = MockMvcRequestBuilders
            .get("/test")
            .accept(MediaType.APPLICATION_JSON)
            .header("Authorization", "Basic dXNlcjo0MjI5NjBhMy1hNWI5LTQ0ZGItYmE5ZS00Y2YxYWVkZmJkYzM=")
            .header("Cookie", "JSESSIONID=41D3890BC53820F014DFD187D250B0FE")
        val response = mockMvc.perform(builder).andReturn().response
        println(response.status)
        println(response.contentAsString)
    }
}
