package com.microservices.kotlin.chapter5.data

import com.microservices.kotlin.chapter5.model.Customer
import com.microservices.kotlin.chapter5.model.Customer.Telephone
import com.microservices.kotlin.chapter5.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DatabaseInitializer {
    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var mongoOperations: ReactiveMongoOperations

    companion object {
        private val initialCustomers = listOf(
                Customer(1, "Puppy", Telephone("977", "9841122030")),
                Customer(2, "Chubbu", Telephone("1", "7312345679")),
                Customer(3, "Buppu", Telephone("100", "123456789")),
                Customer(14, "Dhuppy", null)
        )
    }

    @PostConstruct
    fun initData() {
//        mongoOperations.collectionExists("Customers").subscribe {
//            if (it != true) {
//                mongoOperations.createCollection("Customers").subscribe {
//                    println("Customers collection created..")
//                }
//            } else println("Customers collection already exists")
//        }

        // since we are using a repository, we don't need all the logic to create/check collections
        customerRepository.saveAll(initialCustomers).subscribe {
            println("Default customers created")
        }
    }
}