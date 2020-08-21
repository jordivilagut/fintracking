package com.jordivilagut.fintracking.exceptions

import org.springframework.http.HttpStatus.BAD_REQUEST

class AlreadyRegisteredException(message: String): ApiException(message, BAD_REQUEST)