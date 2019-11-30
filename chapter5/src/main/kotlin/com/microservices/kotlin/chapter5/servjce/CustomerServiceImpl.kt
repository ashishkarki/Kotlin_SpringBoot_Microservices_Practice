package com.microservices.kotlin.chapter5.servjce

import com.microservices.kotlin.chapter5.model.Customer
import com.microservices.kotlin.chapter5.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomerServiceImpl : CustomerService {
    @Autowired
    private lateinit var customerRepository: CustomerRepository

    override fun getCustomer(id: Int): Mono<Customer> = customerRepository.findById(id)
}