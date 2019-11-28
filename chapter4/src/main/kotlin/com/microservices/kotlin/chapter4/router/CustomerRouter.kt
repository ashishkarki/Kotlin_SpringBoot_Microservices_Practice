package com.microservices.kotlin.chapter4.router

import com.microservices.kotlin.chapter4.model.Customer
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Component
class CustomerRouter {

    @Bean
    fun customRoutes() = router {
        "/functional".nest {
            "/customer".nest {
                GET("/") { it: ServerRequest ->
                    ServerResponse.ok().body(
                            Mono.just(Customer(1, "functional web coding")),
                            Customer::class.java
                    )
                }
            }
        }
    }
}