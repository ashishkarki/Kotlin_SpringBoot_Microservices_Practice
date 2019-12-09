package com.microservice.kotlin.chapter10.actuator

import com.microservice.kotlin.chapter10.service.GreetingsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.health.AbstractHealthIndicator
import org.springframework.boot.actuate.health.Health
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class GreetingsServiceHealthIndicator : AbstractHealthIndicator() {

    @Autowired
    private lateinit var greetingsService: GreetingsService

    override fun doHealthCheck(builder: Health.Builder?) {
        val lastMessage = try {
            val message = greetingsService.getGreeting()
            builder?.up()
            message
        } catch (e: Exception) {
            builder?.down()
            "ERROR: $e"
        }

        builder?.withDetail("lastMessage: ", lastMessage)
    }
}