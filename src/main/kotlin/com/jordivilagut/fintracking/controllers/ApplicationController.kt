package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response

interface ApplicationController {

    fun status(): Response<Any>
}