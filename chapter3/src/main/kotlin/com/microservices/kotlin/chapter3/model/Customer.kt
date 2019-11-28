package com.microservices.kotlin.chapter3.model

import com.fasterxml.jackson.annotation.JsonInclude

// this annotation is not needed here, if we include a global configuration in the application.yml file
// @JsonInclude(JsonInclude.Include.NON_NULL)
data class Customer(var id: Int = 0, var name: String = "", var telephone: Telephone? = null)

data class Telephone(var countryCode: String = "", var telephoneNumber: String = "")

data class ErrorResponse(val error: String, val message: String)