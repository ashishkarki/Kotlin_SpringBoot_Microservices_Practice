package com.microservice.kotlin.chapter10.controller

import com.microservice.kotlin.chapter10.service.GreetingsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingsController {

    @Autowired
    private lateinit var greetingsService: GreetingsService

    @GetMapping("/hello")
    fun message() = greetingsService.getGreeting()
}