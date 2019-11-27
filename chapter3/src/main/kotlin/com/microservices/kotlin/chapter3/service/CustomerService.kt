package com.microservices.kotlin.chapter3.service

import com.microservices.kotlin.chapter3.model.Customer

interface CustomerService {
    fun getCustomer(id: Int): Customer?
    fun createCustomer(customer: Customer)
    fun delete(id: Int)
    fun updateCustomer(id: Int, customer: Customer)
    fun searchCustomers(filter: String): List<Customer>
}