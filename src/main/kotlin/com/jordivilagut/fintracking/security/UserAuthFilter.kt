package com.jordivilagut.fintracking.security

import com.jordivilagut.fintracking.services.TokenService
import com.jordivilagut.fintracking.services.UserService
import com.jordivilagut.fintracking.utils.Headers.Companion.AUTH_TOKEN
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class UserAuthFilter

    constructor(
        @Autowired val tokenService: TokenService,
        @Autowired val userService: UserService)

    : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {


        val token = request.getHeader(AUTH_TOKEN);
        var username: String? = null

        if (token != null) {
            try {
                username = tokenService.getUsername(token)
            } catch (e: IllegalArgumentException) {
                println("Unable to get JWT Token");
            } catch (e: ExpiredJwtException) {
                println("JWT Token has expired");
            }
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {

            val user = userService.findByToken(token)

            if (user != null) {
                val springToken = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                springToken.details = WebAuthenticationDetailsSource().buildDetails(request);

                SecurityContextHolder.getContext().authentication = springToken
            }
        }

        chain.doFilter(request, response);
    }
}