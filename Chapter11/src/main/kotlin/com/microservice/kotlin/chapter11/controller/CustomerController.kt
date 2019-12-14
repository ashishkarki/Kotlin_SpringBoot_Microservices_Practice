package com.microservice.kotlin.chapter11.controller

import com.microservice.kotlin.chapter11.model.Customer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {

    companion object {
        private val initialCustomers = arrayOf(
                Customer(1, "Kotlin"),
                Customer(2, "Spring"),
                Customer(3, "Microservice")
        )

        private val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
    }

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int) = customers.get(key = id)

    @GetMapping("/customers")
    fun getAllCustomers() = customers.values.toList()
}