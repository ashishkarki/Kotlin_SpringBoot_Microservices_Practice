package com.microservices.kotlin.chapter4.service

import com.microservices.kotlin.chapter4.model.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustomer(id: Int): Mono<Customer>
    fun searchCustomers(filter: String): Flux<Customer>
    fun createCustomer(customerMono: Mono<Customer>): Mono<*>
}