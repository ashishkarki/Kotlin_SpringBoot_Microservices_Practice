package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

// @Service // if this is commented, we have to provide the service bean explicitly in Main class
class ExampleService : ServiceInterface {
    @Value(value = "\${service.message.text}")
    private lateinit var text: String

    override fun getHello(name: String) = "hello $name from ExampleService using text: $text"
}

