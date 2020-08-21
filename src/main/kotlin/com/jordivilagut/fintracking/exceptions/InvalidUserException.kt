package com.jordivilagut.fintracking.exceptions

import org.springframework.http.HttpStatus.UNAUTHORIZED

class InvalidUserException(message: String): ApiException(message, UNAUTHORIZED)