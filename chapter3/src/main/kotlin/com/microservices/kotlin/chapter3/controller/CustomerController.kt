package com.microservices.kotlin.chapter3.controller

import com.microservices.kotlin.chapter3.model.Customer
import com.microservices.kotlin.chapter3.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {

    @Autowired
    private lateinit var customerService: CustomerService

    // @RequestMapping(value = ["/customer"], method = [RequestMethod.GET])
    @GetMapping(value = ["/customer"])
    fun getCustomerHello() = "Hello from Customer Controller"

    @GetMapping(value = ["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Customer?> {
        val customer = customerService.getCustomer(id)
        val status = if (customer == null) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(customer, status)
    }

    @GetMapping(value = ["/customers"])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") filter: String): ResponseEntity<List<Customer>> {
        var status = HttpStatus.NOT_FOUND
        val foundCustomers = customerService.searchCustomers(filter)

        if (!foundCustomers.isNullOrEmpty()) status = HttpStatus.OK

        return ResponseEntity(foundCustomers, status)
    }

    @PostMapping(value = ["/customer/"])
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Unit> {
        customerService.createCustomer(customer)

        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @DeleteMapping(value = ["/customer/{id}"])
    fun deleteCustomer(@PathVariable id: Int): ResponseEntity<String> {
        var status = HttpStatus.NOT_FOUND
        var message = "entity not found!!"

        if (isCustomerPresent(id)) {
            customerService.delete(id)
            status = HttpStatus.OK
            message = "entity deleted successfully"
        }

        return ResponseEntity(message, status)
    }

    @PutMapping(value = ["/customer/{id}"])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND

        if (isCustomerPresent(id)) {
            customerService.updateCustomer(id, customer)
            status = HttpStatus.ACCEPTED
        }

        return ResponseEntity(Unit, status)
    }

    private fun isCustomerPresent(id: Int): Boolean = customerService.getCustomer(id) != null
}