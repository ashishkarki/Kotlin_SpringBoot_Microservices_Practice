package com.microservice.kotlin.chapter11.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
internal class CustomerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `we should get a list of all customers`() {
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$[0].id").value(1))
                .andExpect(jsonPath("\$[0].name").value("Kotlin"))
                // and few more
                .andDo(print())
    }

    @Test
    fun `we should get a customer by id`() {
        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.id").value(1))
                .andExpect(jsonPath("\$.name").value("Kotlin"))
                .andDo(print())
    }
}