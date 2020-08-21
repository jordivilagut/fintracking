package com.jordivilagut.fintracking.base

import com.jordivilagut.fintracking.exceptions.ApiException

class ResponseMapper {

    companion object {

        fun error(e: ApiException): Response<Any> = Response(e.message, e.status)
    }
}