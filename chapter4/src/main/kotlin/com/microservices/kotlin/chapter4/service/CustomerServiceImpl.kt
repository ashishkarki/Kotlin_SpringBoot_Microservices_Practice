package com.microservices.kotlin.chapter4.service

import com.microservices.kotlin.chapter4.error.CustomerExistException
import com.microservices.kotlin.chapter4.model.Customer
import com.microservices.kotlin.chapter4.model.Customer.Telephone
import org.springframework.stereotype.Service
import reactor.core.Disposable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
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

    override fun getCustomer(id: Int): Mono<Customer> = customers[id]?.toMono()
            ?: Mono.empty() // Mono.just(customers[id]!!)

    override fun searchCustomers(filter: String): Flux<Customer> =
            customers.filter {
                it.value.name.contains(filter, true)
            }.map(Map.Entry<Int, Customer>::value).toFlux()

    override fun createCustomer(customerMono: Mono<Customer>): Mono<Customer> {
        // this will cause a disposable object to be returned back
//        return customerMono.subscribe {
//            customers[it.id] = it
//        }.toMono()

        // if we want to return an empty object, we use a map like so
        //return customerMono.map {
        //customers[it.id] = it
        //it // just an example: this line will cause the resultant object to be returned
        // Mono.empty<Any>() // this line causes an empty object along with any result (result is none if using .map)
        //}

        // a sample of how we can return an exception too
        return customerMono.flatMap {
            if (customers[it.id] == null) {
                customers[it.id] = it
                Mono.just(it)
            } else {
                Mono.error(CustomerExistException("Customer with ${it.id} already exists"))
            }
        }
    }
}