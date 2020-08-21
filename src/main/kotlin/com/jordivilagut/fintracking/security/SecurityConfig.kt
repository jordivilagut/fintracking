package com.jordivilagut.fintracking.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig

    constructor(
        @Autowired val userAuthFilter: UserAuthFilter,
        @Autowired val authEntryPoint: AuthEntryPoint)

    : WebSecurityConfigurerAdapter() {

    companion object {
        private const val LOGIN_URL = "/auth/login"
        private const val LOCAL_URI = "http://localhost:3000"
    }

    override fun configure(http: HttpSecurity) {
        http
                .cors()
                    .configurationSource(corsConfigurationSource())
                    .and()
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers(LOGIN_URL).permitAll()
                    .anyRequest().authenticated()
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(authEntryPoint)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(STATELESS);

        http.addFilterBefore(userAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val source = UrlBasedCorsConfigurationSource()
        val corsConfiguration = CorsConfiguration().applyPermitDefaultValues()
        corsConfiguration.allowedOrigins = arrayListOf(LOCAL_URI)
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }
}