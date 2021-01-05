package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service


@Service
class EmailServiceImpl

    @Autowired
    constructor(
        val mailSender: JavaMailSender,
        val tokenService: TokenService)

    : EmailService {

    override fun sendForgotPasswordEmail(user: User) {
        val userToken = tokenService.createJWT(user)
        val message = SimpleMailMessage()
        message.setFrom("jordi.fintracking@gmail.com")
        message.setTo(user.email)
        message.setSubject("Fintracking: Password Recovery")
        message.setText("Recover password: https://fintracking.netlify.app/recoverpwd/$userToken")
        mailSender.send(message)
    }
}