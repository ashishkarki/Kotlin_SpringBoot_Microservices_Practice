package com.microservices.kotlin.chapter3.controller

import com.microservices.kotlin.chapter3.model.Customer
import com.microservices.kotlin.chapter3.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {

    @Autowired
    private lateinit var customerService: CustomerService

    // @RequestMapping(value = ["/customer"], method = [RequestMethod.GET])
    @GetMapping(value = ["/customer"])
    fun getCustomerHello() = "Hello from Customer Controller"

    @GetMapping(value = ["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int) = customerService.getCustomer(id)

    @GetMapping(value = ["/customers"])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") filter: String) =
            customerService.searchCustomers(filter)

    @PostMapping(value = ["/customer/"])
    fun createCustomer(@RequestBody customer: Customer) = customerService.createCustomer(customer)

    @DeleteMapping(value = ["/customer/{id}"])
    fun deleteCustomer(@PathVariable id: Int) = customerService.delete(id)

    @PutMapping(value = ["/customer/{id}"])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer) =
            customerService.updateCustomer(id, customer)
}