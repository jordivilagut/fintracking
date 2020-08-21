package com.jordivilagut.fintracking.base

import org.springframework.http.HttpStatus.NO_CONTENT

class NoContent: Response<Any>(null, NO_CONTENT)