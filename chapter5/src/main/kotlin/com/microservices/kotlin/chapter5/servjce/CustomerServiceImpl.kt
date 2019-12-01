package com.microservices.kotlin.chapter5.servjce

import com.microservices.kotlin.chapter5.model.Customer
import com.microservices.kotlin.chapter5.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CustomerServiceImpl : CustomerService {
    @Autowired
    private lateinit var customerRepository: CustomerRepository

    override fun getCustomer(id: Int): Mono<Customer> = customerRepository.findById(id)

    override fun createCustomer(customer: Mono<Customer>): Mono<Customer> = customerRepository.create(customer)

    override fun deleteCustomer(id: Int): Mono<Boolean> =
            customerRepository.deleteById(id).map { it.deletedCount > 0 }

    override fun searchCustomers(filter: String): Flux<Customer> = customerRepository.findCustomers(filter)
}