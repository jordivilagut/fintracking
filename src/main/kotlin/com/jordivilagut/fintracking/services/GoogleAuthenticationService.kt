package com.jordivilagut.fintracking.services

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken

interface GoogleAuthenticationService {

    fun getPayloadFromToken(idToken: String): GoogleIdToken.Payload
}