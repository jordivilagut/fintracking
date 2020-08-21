package com.jordivilagut.fintracking.base

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open class Response<T>(body: T?, status: HttpStatus): ResponseEntity<T>(body, status)