package com.microservices.kotlin.chapter4.router

import com.microservices.kotlin.chapter4.model.Customer
import com.microservices.kotlin.chapter4.service.CustomerService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class CustomerHandler(private val customerService: CustomerService) {
    fun get(serverRequest: ServerRequest) =
//            ok().body(customerService.getCustomer(serverRequest.pathVariable("id").toInt())
//                    /*, Customer::class.java*/)
            customerService.getCustomer(serverRequest.pathVariable("id").toInt())
                    .flatMap { ok().body(fromObject(it)) }
                    .switchIfEmpty(notFound().build())

    fun search(serverRequest: ServerRequest) =
            ok().body(customerService.searchCustomers(
                    serverRequest.queryParam("filter").orElse("")),
                    Customer::class.java)
}