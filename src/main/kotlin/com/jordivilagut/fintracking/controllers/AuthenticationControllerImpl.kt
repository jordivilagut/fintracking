package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.NoContent
import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.base.ResponseMapper.Companion.error
import com.jordivilagut.fintracking.exceptions.ApiException
import com.jordivilagut.fintracking.model.dto.UserCredentials
import com.jordivilagut.fintracking.services.AuthenticationService
import com.jordivilagut.fintracking.utils.Headers.Companion.AUTH_TOKEN
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("auth")
class AuthenticationControllerImpl

        @Autowired
        constructor(
                val authService: AuthenticationService)

    : AuthenticationController {

        @PostMapping("/login")
        override fun login(
                @RequestHeader(AUTH_TOKEN) token: String?,
                @RequestBody credentials: UserCredentials?)

        : Response<Any> {

            return try {
                val auth = authService.login(token, credentials)
                return Response(auth, OK)

            } catch (e: ApiException) { error(e) }
        }

        @PostMapping("/signup")
        override fun signup(
                @RequestBody credentials: UserCredentials)

        : Response<Any> {

            return try {
                val auth = authService.register(credentials)
                Response(auth, OK)

            } catch (e: ApiException) { error(e) }
        }

        @DeleteMapping
        override fun logout(
                @RequestHeader(AUTH_TOKEN) token: String)

        : Response<Any> {

            return try {
                authService.logout(token)
                NoContent()

            } catch (e: ApiException) { error(e) }
        }
}