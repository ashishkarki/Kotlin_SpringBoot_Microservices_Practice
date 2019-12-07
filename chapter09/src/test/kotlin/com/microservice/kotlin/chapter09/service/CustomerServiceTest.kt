package com.microservice.kotlin.chapter09.service


import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    lateinit var customerServiceImpl: CustomerServiceImpl

    @Test
    fun getCustomer() {
        customerServiceImpl.getCustomer(1)
    }

    @Test
    fun getAllCustomers() {
        customerServiceImpl.getAllCustomers()
    }
}