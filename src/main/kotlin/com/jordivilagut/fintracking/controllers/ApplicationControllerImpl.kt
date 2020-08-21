package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApplicationControllerImpl : ApplicationController {

    @GetMapping("status")
    override fun status(): Response<Any> {
        return Response("Fintracking 1.0" , OK)
    }
}