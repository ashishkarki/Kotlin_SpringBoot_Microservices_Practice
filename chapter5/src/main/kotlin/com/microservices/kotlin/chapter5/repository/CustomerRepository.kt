package com.microservices.kotlin.chapter5.repository

import com.microservices.kotlin.chapter5.model.Customer
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CustomerRepository : ReactiveCrudRepository<Customer, Int> {
}