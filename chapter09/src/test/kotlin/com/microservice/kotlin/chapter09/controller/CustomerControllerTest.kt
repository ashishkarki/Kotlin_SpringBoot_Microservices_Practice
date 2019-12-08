package com.microservice.kotlin.chapter09.controller

import com.microservice.kotlin.chapter09.model.Customer
import com.microservice.kotlin.chapter09.service.CustomerService
import org.amshove.kluent.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultHandler
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.JsonPathResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class WithKeyword {
    infix fun `json path`(expression: String) = jsonPath("\$" + expression)
}

val With = WithKeyword()

class ThatKeyword {
    infix fun `status is http`(value: Int) = status().`is`(value)
}

val That = ThatKeyword()

infix fun JsonPathResultMatchers.`that the value is`(value: Any) = this.value(value)
infix fun ResultActions.`and expect`(matcher: ResultMatcher) = this.andExpect(matcher)
infix fun ResultActions.`and then do`(handler: ResultHandler) = this.andDo(handler)
infix fun MockMvc.`do a get request to`(uri: String) = this.perform(get(uri))

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var customerService: CustomerService

    @Test
    fun `mock mvc should be configured`() {

    }

    @Test
    fun `we should GET a customer by Id`() {
        val mockName = "Mock Customer"
//        given(customerService.getCustomer(1))
//                .willReturn(Customer(1, mockName))

        When calling customerService.getCustomer(1) `it returns`
                Customer(1, mockName)

//        mockMvc.perform(get("/customer/1"))
//                .andExpect(status().isOk)
//                .andExpect(jsonPath("\$.id").value(1))
//                .andExpect(jsonPath("\$.name").value(mockName))
//                .andDo(print())
        (mockMvc `do a get request to` "/customer/1"
                `and expect` (That `status is http` 200)
                `and expect` (With `json path` ".id" `that the value is` 1)
                `and expect` (With `json path` ".name" `that the value is` mockName)
                ) `and then do` print()


//        then(customerService).should(times(1)).getCustomer(1)
//        then(customerService).shouldHaveNoMoreInteractions()
        Verify on customerService that customerService.getCustomer(1) was (called)
        `Verify no further interactions` on customerService

        reset(customerService)
    }

    @Test
    fun `we should GET a list of customers`() {
        // let's use Kluent assertions instead of this
//        given(customerService.getAllCustomers())
//                .willReturn(listOf(Customer(1, "test1"), Customer(2, "test2")))

        When calling customerService.getAllCustomers() `it returns`
                listOf(Customer(1, "test1"), Customer(2, "test2"))

//        mockMvc.perform(get("/customers"))
//                .andExpect(status().isOk)
//                .andExpect(jsonPath("\$").isArray)
//                .andExpect(jsonPath("\$[0].id").value(1))
//                .andExpect(jsonPath("\$[0].name").value("test1"))
//                .andExpect(jsonPath("\$[1].id").value(2))
//                .andExpect(jsonPath("\$[1].name").value("test2"))
//                .andDo(print())
        (mockMvc `do a get request to` "/customers"
                `and expect` (That `status is http` 200)
                `and expect` (With `json path` "[0].id" `that the value is` 1)
                `and expect` (With `json path` "[0].name" `that the value is`
                "test1")
                `and expect` (With `json path` "[1].id" `that the value is` 2)
                `and expect` (With `json path` "[1].name" `that the value is`
                "test2")
                ) `and then do` print()


        // also, use Kluent assertion here
//        then(customerService).should(times(1)).getAllCustomers()
//        then(customerService).shouldHaveNoMoreInteractions()
        Verify on customerService that customerService.getAllCustomers() was (called)
        `Verify no further interactions` on customerService

        reset(customerService)
    }
}