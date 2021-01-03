package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.NoContent
import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.base.ResponseMapper.Companion.error
import com.jordivilagut.fintracking.controllers.AuthenticationController.Companion.PATH
import com.jordivilagut.fintracking.exceptions.ApiException
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.CreateUser
import com.jordivilagut.fintracking.model.dto.UserCredentials
import com.jordivilagut.fintracking.services.AuthenticationService
import com.jordivilagut.fintracking.utils.Headers.Companion.AUTH_TOKEN
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(PATH)
class AuthenticationControllerImpl

    @Autowired
    constructor(
        val authService: AuthenticationService)

    : AuthenticationController {

    @PostMapping("/login")
    override fun login(
        @RequestHeader(AUTH_TOKEN) token: String?,
        @RequestBody credentials: UserCredentials?): Response<Any> {

        return try {
            val auth = authService.login(token, credentials)
            return Response(auth, OK)

        } catch (e: ApiException) {
            error(e)
        }
    }

    @PostMapping("/signup")
    override fun signup(
        @RequestBody credentials: CreateUser): Response<Any> {

        return try {
            val auth = authService.register(credentials)
            Response(auth, OK)

        } catch (e: ApiException) {
            error(e)
        }
    }

    @PostMapping("/forgot-pwd")
    override fun sendForgotPasswordEmail(
        @RequestBody email: String): Response<Any> {

        return try {
            authService.sendForgotPasswordEmail(email)
            Response(null, NO_CONTENT)

        } catch (e: ApiException) {
            error(e)
        }
    }

    @DeleteMapping
    override fun logout(
        @AuthenticationPrincipal user: User): Response<Any> {

        return try {
            authService.logout(user)
            NoContent()

        } catch (e: ApiException) {
            error(e)
        }
    }
}