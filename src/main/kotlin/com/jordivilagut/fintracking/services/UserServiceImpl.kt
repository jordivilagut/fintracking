package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.adapters.UserAdapter.Companion.toUser
import com.jordivilagut.fintracking.exceptions.AlreadyRegisteredException
import com.jordivilagut.fintracking.exceptions.InvalidUserException
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.UserCredentials
import com.jordivilagut.fintracking.repositories.users.UserRepository
import com.jordivilagut.fintracking.services.AuthenticationServiceImpl.Companion.EMAIL_REGEX
import com.jordivilagut.fintracking.services.AuthenticationServiceImpl.Companion.PASSWORD_REGEX
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class UserServiceImpl

    @Autowired
    constructor(
            val userRepository: UserRepository)

    : UserService  {

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun findByToken(token: String): User? {
        return userRepository.findByToken(token)
    }

    override fun findByFilter(filter: UserService.Filter): List<User> {
        TODO("Not yet implemented")
    }

    override fun createUser(credentials: UserCredentials): User {
        val email = credentials.email
        val password = credentials.password

        if (email.isBlank() || password.isBlank())                  throw InvalidUserException("Empty username or password.")
        if (findByEmail(email) != null)                             throw AlreadyRegisteredException("Username already registered.")
        if (!password.matches(PASSWORD_REGEX))                      throw InvalidUserException("Password is too short.")
        if (!email.matches(EMAIL_REGEX))                            throw InvalidUserException("Invalid email.")

        val encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
        val user = toUser(email, encryptedPassword)
        return userRepository.save(user)
    }

    override fun updateToken(user: User, token: String?) {
        user.token = token
        userRepository.save(user)
    }

    override fun revokeToken(user: User) {
        updateToken(user, null)
    }
}