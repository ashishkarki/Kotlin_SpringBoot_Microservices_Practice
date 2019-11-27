package com.microservices.kotlin.chapter3

import com.microservices.kotlin.chapter3.model.Customer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class Chapter3Application {
    companion object {
        val initialCustomers = arrayOf(
                Customer(1, "Puppy"),
                Customer(2, "Chubbu"),
                Customer(3, "Buppu")
        )
    }

    @Bean
    fun customers() = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
}

fun main(args: Array<String>) {
    runApplication<Chapter3Application>(*args)
}
