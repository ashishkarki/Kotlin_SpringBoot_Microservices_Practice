package com.microservices.kotlin.chapter4.error

class CustomerExistException(override val message: String) : Exception(message)