package com.microservice.kotlin.chapter09.controller

import com.microservice.kotlin.chapter09.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {

    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int) = customerService.getCustomer(id)

    @GetMapping("/customers")
    fun getAllCustomers() = customerService.getAllCustomers()
}