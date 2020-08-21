package com.jordivilagut.fintracking.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document("users")
data class User(
        @Id val id: ObjectId?,
        val email: String,
        private val password: String,
        var token: String?

): UserDetails {

    override fun getUsername() = email

    override fun getPassword() = password

    override fun getAuthorities() = HashSet<GrantedAuthority>()

    override fun isEnabled() = true

    override fun isCredentialsNonExpired() = true

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}