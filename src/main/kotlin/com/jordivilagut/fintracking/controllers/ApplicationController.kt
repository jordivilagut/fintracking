package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response

interface ApplicationController {

    companion object {
        const val STATUS = "status"
    }

    fun status(): Response<Any>
}