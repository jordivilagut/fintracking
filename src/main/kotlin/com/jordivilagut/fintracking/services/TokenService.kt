package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.User
import io.jsonwebtoken.Claims

interface TokenService {

    companion object {
        const val SUBJECT: String = "token-subject"
        const val ISSUER: String = "issuer-name"
        const val SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w"
        const val EXPIRATION_TIME: Long = 604800000 //One week
    }

    fun createJWT(user: User): String

    fun decodeJWT(jwt: String): Claims

    fun getUsername(jwt: String): String

    fun isExpired(jwt: String): Boolean
}