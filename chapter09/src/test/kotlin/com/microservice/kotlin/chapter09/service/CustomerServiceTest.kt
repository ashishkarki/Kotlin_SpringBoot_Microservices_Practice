package com.microservice.kotlin.chapter09.service

import org.amshove.kluent.*
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
    fun `we should get a customer with a valid Id`() {
        val customer = customerService.getCustomer(1)

        // assertNotNull(customer) // use Kluent assertion instead of this; more expressive
        // customer.shouldNotBeNull()
        customer.`should not be null`() //can use words in Kluent

        // assertEquals(customer?.name, "Kotlin")
        customer?.name `should be` "Kotlin"
    }

    @Test
    fun `we should get all customers`() {
        val customers = customerService.getAllCustomers()

        // assertTrue(customers.size == 3) // use Kluent assertions
        customers.size `should equal to` 3
        customers.size `should be greater than` 0
        customers.size `should be less or equal to` 3
    }
}