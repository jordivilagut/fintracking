package com.jordivilagut.fintracking.services

import org.springframework.stereotype.Service

@Service
class EmailServiceImpl : EmailService {

    override fun sendForgotPasswordEmail(email: String) {
        println("Send email to $email")
    }
}