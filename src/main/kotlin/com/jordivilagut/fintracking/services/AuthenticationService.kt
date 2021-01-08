package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.Auth
import com.jordivilagut.fintracking.model.dto.CreateUser
import com.jordivilagut.fintracking.model.dto.GoogleAuth
import com.jordivilagut.fintracking.model.dto.UserCredentials

interface AuthenticationService {

    fun login(token: String?, credentials: UserCredentials?): Auth

    fun register(credentials: CreateUser): Auth

    fun loginWithGoogle(googleAuth: GoogleAuth): Auth

    fun signupWithGoogle(googleAuth: GoogleAuth): Auth

    fun logout(user: User)

    fun sendForgotPasswordEmail(email: String)
}