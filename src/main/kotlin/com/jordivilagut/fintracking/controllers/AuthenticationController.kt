package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.dto.UserCredentials

interface AuthenticationController {

    fun login(token: String?, credentials: UserCredentials?): Response<Any>

    fun signup(credentials: UserCredentials): Response<Any>

    fun logout(token: String): Response<Any>
}