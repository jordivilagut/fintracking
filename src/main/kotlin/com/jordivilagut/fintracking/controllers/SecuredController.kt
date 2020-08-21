package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.User


interface SecuredController {

    fun sayHi(user: User): Response<String>
}