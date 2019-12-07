package com.microservice.kotlin.chapter09.service

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    lateinit var customerService: CustomerService

    @Test
    fun `we should get a customer with a valid name`() {
        val customer = customerService.getCustomer(1)
        assertNotNull(customer)
        assertEquals(customer?.name, "Kotlin")
    }

    @Test
    fun `we should get all customers`() {
        val customers = customerService.getAllCustomers()
        assertTrue(customers.size == 3)
    }
}