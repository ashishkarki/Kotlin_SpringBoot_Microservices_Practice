package com.microservices.kotlin.chapter5.handler

import com.microservices.kotlin.chapter5.servjce.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class CustomerHandler(val customerService: CustomerService) {

    fun get(serverRequest: ServerRequest) =
            customerService.getCustomer(serverRequest.pathVariable("id").toInt())
                    .flatMap { ServerResponse.ok().body(fromObject(it)) }
                    .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build())
}