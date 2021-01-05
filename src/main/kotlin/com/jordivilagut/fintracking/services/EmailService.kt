package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.User

interface EmailService {

    fun sendForgotPasswordEmail(user: User)
}