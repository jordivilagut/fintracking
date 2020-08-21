package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.exceptions.InvalidUserException
import com.jordivilagut.fintracking.model.dto.Auth
import com.jordivilagut.fintracking.model.dto.UserCredentials
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl

    @Autowired
    constructor(
            val userService: UserService,
            val tokenService: TokenService)

    : AuthenticationService {

    companion object {
        val EMAIL_REGEX = Regex("^(.+)@(.+)$")
    }

    override fun login(token: String?, credentials: UserCredentials?): Auth {
        return  if (!token.isNullOrBlank()) autoLogin(token)
                else credentialsLogin(credentials!!)
    }

    override fun register(credentials: UserCredentials): Auth {
        val user = userService.createUser(credentials)
        return credentialsLogin(UserCredentials(user.email, user.password))
    }

    override fun logout(token: String) {
        val user = userService.findByToken(token) ?: throw InvalidUserException("User not found.")
        userService.revokeToken(user)
    }

    private fun autoLogin(token: String): Auth {
        val user = userService.findByToken(token) ?: throw InvalidUserException("User not found.")
        return Auth(user.email, user.token!!)
    }

    private fun credentialsLogin(credentials: UserCredentials): Auth {
        val user = userService.findByEmail(credentials.email)?:     throw InvalidUserException("Invalid email.")
        if (user.password != credentials.password)                  throw InvalidUserException("Invalid password.")

        val token = tokenService.createJWT(user)
        userService.updateToken(user, token)

        return Auth(user.email, user.token!!)
    }
}