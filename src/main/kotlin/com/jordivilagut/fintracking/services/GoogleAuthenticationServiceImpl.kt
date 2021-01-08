package com.jordivilagut.fintracking.services

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.jordivilagut.fintracking.ApplicationProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Collections.singletonList


@Service
class GoogleAuthenticationServiceImpl

    @Autowired
    constructor(
        val appProperties: ApplicationProperties)

    : GoogleAuthenticationService {

    override fun getPayloadFromToken(idToken: String): GoogleIdToken.Payload {

        val verifier: GoogleIdTokenVerifier =
            GoogleIdTokenVerifier.Builder(NetHttpTransport(), JacksonFactory())
                .setAudience(singletonList(appProperties.googleClientId))
                .build()

        val verifiedToken = verifier.verify(idToken)?: throw IllegalArgumentException("Invalid ID token.")
        return verifiedToken.payload
    }
}