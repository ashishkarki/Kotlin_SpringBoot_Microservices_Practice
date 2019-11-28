package com.microservices.kotlin.chapter3.service

import com.microservices.kotlin.chapter3.model.Customer
import com.microservices.kotlin.chapter3.model.Telephone
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomers = arrayOf(
                Customer(1, "Puppy", Telephone("977", "9841122030")),
                Customer(2, "Chubbu", Telephone("1", "7312345679")),
                Customer(3, "Buppu", Telephone("100", "123456789")),
                Customer(14, "Dhuppy", null)
        )
    }

    private val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int): Customer? = customers[id]

    override fun createCustomer(customer: Customer) {
        customers[customer.id] = customer
    }

    override fun delete(id: Int) {
        customers.remove(id)
    }

    override fun updateCustomer(id: Int, customer: Customer) {
        delete(id)
        createCustomer(customer)
    }

    override fun searchCustomers(filter: String): List<Customer> =
            customers.filter {
                it.value.name.contains(filter, true)
            }.map(Map.Entry<Int, Customer>::value).toList()
}