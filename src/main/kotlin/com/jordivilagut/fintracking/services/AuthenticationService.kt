package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.dto.Auth
import com.jordivilagut.fintracking.model.dto.UserCredentials

interface AuthenticationService {

    fun login(token: String?, credentials: UserCredentials?): Auth

    fun register(credentials: UserCredentials): Auth

    fun logout(token: String)
}