package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.base.ResponseBody
import com.jordivilagut.fintracking.controllers.SecuredController.Companion.PATH
import com.jordivilagut.fintracking.model.User
import org.springframework.http.HttpStatus.OK
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(PATH)
class SecuredControllerImpl : SecuredController {

    @GetMapping
    override fun getSecretMessage(
        @AuthenticationPrincipal user: User): Response<Any> {

        val body = ResponseBody("This is a secret message only " + user.email + " can see.")
        return Response(body, OK)
    }
}