package com.microservices.kotlin.chapter5.servjce

import com.microservices.kotlin.chapter5.model.Customer
import reactor.core.publisher.Mono

interface CustomerService {

    fun getCustomer(id: Int): Mono<Customer>
}