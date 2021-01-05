package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.exceptions.InvalidUserException
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.Auth
import com.jordivilagut.fintracking.model.dto.CreateUser
import com.jordivilagut.fintracking.model.dto.UserCredentials
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCrypt.checkpw
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl

    @Autowired
    constructor(
            val userService: UserService,
            val tokenService: TokenService,
            val emailService: EmailService)

    : AuthenticationService {

    companion object {
        val EMAIL_REGEX = Regex("^(.+)@(.+)$")
        val PASSWORD_REGEX = Regex("^[a-zA-Z0-9]{8,}$")
    }

    override fun login(token: String?, credentials: UserCredentials?): Auth {
        return  if (!token.isNullOrBlank()) autoLogin(token)
                else credentialsLogin(credentials!!)
    }

    override fun register(credentials: CreateUser): Auth {
        val user = userService.createUser(credentials)
        return refreshToken(user)
    }

    override fun logout(user: User) {
        refreshToken(user)
    }

    override fun sendForgotPasswordEmail(email: String) {
        val user = userService.findByEmail(email)?: throw InvalidUserException("User not found.")
        emailService.sendForgotPasswordEmail(user)
    }

    private fun autoLogin(token: String): Auth {
        val user = userService.findByToken(token) ?: throw InvalidUserException("User not found.")
        return Auth(user.name, user.email, user.token!!)
    }

    private fun credentialsLogin(credentials: UserCredentials): Auth {
        val user = userService.findByEmail(credentials.email)?:     throw InvalidUserException("Invalid email.")
        if (!checkpw(credentials.password, user.password))          throw InvalidUserException("Invalid password.")
        return refreshToken(user)
    }

    private fun refreshToken(user: User): Auth {
        val token = tokenService.createJWT(user)
        userService.updateToken(user, token)

        return Auth(user.name, user.email, user.token!!)
    }
}