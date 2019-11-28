package com.microservices.kotlin.chapter4.service

import com.microservices.kotlin.chapter4.model.Customer
import com.microservices.kotlin.chapter4.model.Customer.Telephone
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl : CustomerService {
    companion object {
        private val initialCustomers = arrayOf(
                Customer(1, "Puppy", Telephone("977", "9841122030")),
                Customer(2, "Chubbu", Telephone("1", "7312345679")),
                Customer(3, "Buppu", Telephone("100", "123456789")),
                Customer(14, "Dhuppy", null)
        )

        val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
    }

    override fun getCustomer(id: Int): Mono<Customer?> = Mono.just(customers[id]!!)

    override fun searchCustomers(filter: String): Flux<Customer> =
            customers.filter {
                it.value.name.contains(filter, true)
            }.map(Map.Entry<Int, Customer>::value).toFlux()
}