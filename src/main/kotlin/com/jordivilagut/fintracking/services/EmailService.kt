package com.jordivilagut.fintracking.services

interface EmailService {

    fun sendForgotPasswordEmail(email: String)
}