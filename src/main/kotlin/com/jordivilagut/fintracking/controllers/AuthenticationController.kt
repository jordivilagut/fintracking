package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.CreateUser
import com.jordivilagut.fintracking.model.dto.UserCredentials

interface AuthenticationController {

    companion object {
        const val PATH = "auth"
    }

    fun login(token: String?, credentials: UserCredentials?): Response<Any>

    fun signup(credentials: CreateUser): Response<Any>

    fun loginWithGoogle(googleIdToken: String): Response<Any>

    fun signupWithGoogle(googleIdToken: String): Response<Any>

    fun logout(user: User): Response<Any>

    fun sendForgotPasswordEmail(email: String): Response<Any>

    fun changePassword(user: User, password: String): Response<Any>
}