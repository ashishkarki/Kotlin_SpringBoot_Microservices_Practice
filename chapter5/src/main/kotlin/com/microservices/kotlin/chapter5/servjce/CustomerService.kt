package com.microservices.kotlin.chapter5.servjce

import com.microservices.kotlin.chapter5.model.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {

    fun getCustomer(id: Int): Mono<Customer>
    fun createCustomer(customer: Mono<Customer>): Mono<Customer>
    fun deleteCustomer(id: Int): Mono<Boolean>
    fun searchCustomers(filter: String): Flux<Customer>
}