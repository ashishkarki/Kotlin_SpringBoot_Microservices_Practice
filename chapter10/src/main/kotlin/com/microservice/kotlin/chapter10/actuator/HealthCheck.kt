package com.microservice.kotlin.chapter10.actuator

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class HealthCheck : HealthIndicator {
    override fun health(): Health {
        val errorCode = check() // perform some specific health check
        return if (errorCode != 0) {
            Health.down()
                    .withDetail("Error Code", errorCode).build()
        } else Health.up().build()
    }

    fun check(): Int { // Our logic to check health
        return 1
    }
}