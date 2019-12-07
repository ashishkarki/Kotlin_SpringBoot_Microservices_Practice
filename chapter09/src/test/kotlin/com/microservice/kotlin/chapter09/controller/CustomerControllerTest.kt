package com.microservice.kotlin.chapter09.controller

import com.microservice.kotlin.chapter09.model.Customer
import com.microservice.kotlin.chapter09.service.CustomerService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.boot.test.mock.mockito.MockBean
import org.mockito.Mockito

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var customerService: CustomerService

    @Test
    fun `mock mvc should be configured`() {

    }

    @Test
    fun `we should GET a customer by Id`() {
        val mockName = "Mock Customer"
        given(customerService.getCustomer(1))
                .willReturn(Customer(1, mockName))

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.id").value(1))
                .andExpect(jsonPath("\$.name").value(mockName))
                .andDo(print())

        then(customerService).should(times(1)).getCustomer(1)
        then(customerService).shouldHaveNoMoreInteractions()

        reset(customerService)
    }

    @Test
    fun `we should GET a list of customers`() {
        given(customerService.getAllCustomers())
                .willReturn(listOf(Customer(1, "test1"), Customer(2, "test2")))

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$").isArray)
                .andExpect(jsonPath("\$[0].id").value(1))
                .andExpect(jsonPath("\$[0].name").value("Kotlin"))
                .andExpect(jsonPath("\$[1].id").value(2))
                .andExpect(jsonPath("\$[1].name").value("Spring"))
                .andExpect(jsonPath("\$[2].id").value(3))
                .andExpect(jsonPath("\$[2].name").value("Microservice"))
                .andDo(print())

        then(customerService).should(times(1)).getAllCustomers()
        then(customerService).shouldHaveNoMoreInteractions()

        reset(customerService)
    }
}