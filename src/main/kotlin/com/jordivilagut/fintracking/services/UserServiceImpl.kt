package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.adapters.UserAdapter.Companion.toUser
import com.jordivilagut.fintracking.exceptions.AlreadyRegisteredException
import com.jordivilagut.fintracking.exceptions.InvalidUserException
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.UserCredentials
import com.jordivilagut.fintracking.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl

    constructor(
            @Autowired val userRepository: UserRepository)

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
        if (password.length < 4)                                    throw InvalidUserException("Password is too short.")
        if (!email.matches(AuthenticationServiceImpl.EMAIL_REGEX))  throw InvalidUserException("Invalid email.")

        val user = toUser(email, password)
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