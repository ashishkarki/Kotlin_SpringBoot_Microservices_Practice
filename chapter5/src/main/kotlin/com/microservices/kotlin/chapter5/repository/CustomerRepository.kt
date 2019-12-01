package com.microservices.kotlin.chapter5.repository

import com.microservices.kotlin.chapter5.model.Customer
import com.microservices.kotlin.chapter5.model.Customer.Telephone
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import javax.annotation.PostConstruct

//interface CustomerRepository : ReactiveCrudRepository<Customer, Int> {
//}

@Repository
class CustomerRepository(private val template: ReactiveMongoTemplate) {

    companion object {
        private val initialCustomers = listOf(
                Customer(1, "Puppy", Telephone("977", "9841122030")),
                Customer(2, "Chubbu", Telephone("1", "7312345679")),
                Customer(3, "Buppu", Telephone("100", "123456789")),
                Customer(14, "Dhuppy", null)
        )
    }

    @PostConstruct
    fun initializeRepo() = initialCustomers.map { customer -> Mono.just(customer) }
            .map(this::create)
            .map(Mono<Customer>::subscribe)

    fun create(customer: Mono<Customer>) = template.save(customer)

    fun findById(id: Int) = template.findById<Customer>(id)

    // somehow this is throwing no object error
    fun deleteById(id: Int) = template.remove(Query(where("_id").isEqualTo(id)))

    fun findCustomers(filter: String) =
            template.find<Customer>(Query(where("name").regex(".*$filter.*", "i")))
}