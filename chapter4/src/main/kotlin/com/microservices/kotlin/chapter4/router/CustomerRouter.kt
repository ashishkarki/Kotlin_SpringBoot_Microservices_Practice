package com.microservices.kotlin.chapter4.router

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {

//    @Autowired
//    private lateinit var customerHandler: CustomerHandler

    @Bean
    fun customRoutes() = router {
        "/functional".nest {
            "/customer".nest {
                //                GET("/") { it: ServerRequest ->
//                    customerHandler.get(it)
//                }
                GET("/{id}", customerHandler::get)
                POST("/", customerHandler::create)
            }
            "/customers".nest {
                GET("/", customerHandler::search)
            }
        }
    }
}