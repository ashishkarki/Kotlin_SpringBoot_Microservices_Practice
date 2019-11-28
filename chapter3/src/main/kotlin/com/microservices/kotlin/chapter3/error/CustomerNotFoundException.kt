package com.microservices.kotlin.chapter3.error

import kotlin.Exception


class CustomerNotFoundException(message: String): Exception(message) {
}