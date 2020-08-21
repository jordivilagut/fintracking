package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.User
import org.springframework.http.HttpStatus.OK
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hello")
class SecuredControllerImpl : SecuredController {

    @GetMapping
    override fun sayHi(
            @AuthenticationPrincipal user: User)

    : Response<String> {

        return Response("Hi " + user.email, OK)
    }
}