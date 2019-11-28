package com.microservices.kotlin.chapter4.router

import com.microservices.kotlin.chapter4.model.Customer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class CustomerHandler {
    fun get(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().body(Mono.just(Customer(1, "functional web")), Customer::class.java)
    }
}