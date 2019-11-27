package com.microservices.kotlin.chapter3.controller

import com.microservices.kotlin.chapter3.model.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {

    @Autowired
    lateinit var customers: ConcurrentHashMap<Int, Customer>

    // @RequestMapping(value = ["/customer"], method = [RequestMethod.GET])
    @GetMapping(value = ["/customer"])
    fun getCustomerHello() = "Hello from Customer Controller"

    @GetMapping(value = ["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int) = customers[id]

    @GetMapping(value = ["/customers"])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") filter: String) =
            customers.filter {
                it.value.name.contains(filter, true)
            }.map(Map.Entry<Int, Customer>::value).toList()

    @PostMapping(value = ["/customer/"])
    fun createCustomer(@RequestBody customer: Customer) = apply { customers[customer.id] = customer }

    @DeleteMapping(value = ["/customer/{id}"])
    fun deleteCustomer(@PathVariable id: Int) = customers.remove(id)

    @PutMapping(value = ["/customer/{id}"])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer) {
        customers.remove(id)
        customers[id] = customer
    }
}