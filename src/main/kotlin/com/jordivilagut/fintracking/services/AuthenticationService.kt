package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.Auth
import com.jordivilagut.fintracking.model.dto.CreateUser
import com.jordivilagut.fintracking.model.dto.UserCredentials

interface AuthenticationService {

    fun login(token: String?, credentials: UserCredentials?): Auth

    fun register(credentials: CreateUser): Auth

    fun loginWithGoogle(idToken: String): Auth

    fun signupWithGoogle(idToken: String): Auth

    fun logout(user: User)

    fun sendForgotPasswordEmail(email: String)
}