package com.jordivilagut.fintracking.exceptions

import org.springframework.http.HttpStatus

open class ApiException(
        message: String,
        val status: HttpStatus
): Exception(message)