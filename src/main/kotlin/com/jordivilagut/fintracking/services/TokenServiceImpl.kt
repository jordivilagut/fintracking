package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.services.TokenService.Companion.EXPIRATION_TIME
import com.jordivilagut.fintracking.services.TokenService.Companion.ISSUER
import com.jordivilagut.fintracking.services.TokenService.Companion.SECRET_KEY
import com.jordivilagut.fintracking.services.TokenService.Companion.SUBJECT
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Service
class TokenServiceImpl : TokenService {

    override fun createJWT(user: User): String {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val now = Date()
        val exp = Date(now.time + EXPIRATION_TIME)
        val signingKey: Key = SecretKeySpec(SECRET_KEY.toByteArray(), signatureAlgorithm.jcaName)

        return Jwts.builder()
                .setId(user.email)
                .setIssuedAt(now)
                .setSubject(SUBJECT)
                .setIssuer(ISSUER)
                .setExpiration(exp)
                .signWith(signatureAlgorithm, signingKey)
                .compact()
    }

    override fun decodeJWT(jwt: String): Claims {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.toByteArray())
                .parseClaimsJws(jwt).body
    }

    override fun getUsername(jwt: String): String {
        return decodeJWT(jwt).id
    }

    override fun isExpired(jwt: String): Boolean {
        return Date().after(decodeJWT(jwt).expiration)
    }
}