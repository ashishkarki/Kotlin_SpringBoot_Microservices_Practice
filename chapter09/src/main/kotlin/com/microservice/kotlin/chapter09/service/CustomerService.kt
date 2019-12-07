package com.microservice.kotlin.chapter09.service

import com.microservice.kotlin.chapter09.model.Customer

interface CustomerService {

    fun getCustomer(id: Int): Customer?
    fun getAllCustomers(): List<Customer>
}