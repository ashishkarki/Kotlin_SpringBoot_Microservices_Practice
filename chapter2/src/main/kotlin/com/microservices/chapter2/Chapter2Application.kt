package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class Chapter2Application {
    // if a bean hasn't been explicitly annotated, we have to provide them here
    @Bean
	@ConditionalOnExpression("#{'\${service.type}'=='simple'}")
    fun exampleService(): ServiceInterface = ExampleService()

	@Bean
	@ConditionalOnExpression("#{'\${service.type}'=='advanced'}")
	fun advancedService(): ServiceInterface = AdvancedService()
}

@Controller
class FirstController {
    @Autowired
    lateinit var service: ServiceInterface // @autowired will takecare of initializing this late init

    @RequestMapping(value = ["/first/{name}"], method = [RequestMethod.GET])
    @ResponseBody
    fun hello(@PathVariable name: String) = service.getHello(name)
}

fun main(args: Array<String>) {
    runApplication<Chapter2Application>(*args)
}

//@RestController
//class SecondController(val exampleService: ExampleService) {
//
//    @GetMapping(path = ["/second/{name}"])
//    fun secondHello(@PathVariable name: String) = exampleService.getHello(name)
//}
