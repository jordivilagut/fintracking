package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.User


interface SecuredController {

    companion object {
        const val PATH = "hello"
    }

    fun getSecretMessage(user: User): Response<Any>
}