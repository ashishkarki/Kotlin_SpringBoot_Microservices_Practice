package com.microservices.kotlin.chapter3.error

import com.fasterxml.jackson.core.JsonParseException
import com.microservices.kotlin.chapter3.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(servletRequest: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("JSON Error!!", exception.message ?: "invalid json"),
                HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(java.lang.Exception::class)
    fun GenericExceptionHandler(servletRequest: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("Some error occured", exception.message ?: "Error, Please contact support"),
                HttpStatus.I_AM_A_TEAPOT) // basically, server doesn't know how to handle this
    }
}