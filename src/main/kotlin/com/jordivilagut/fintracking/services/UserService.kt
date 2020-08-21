package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.base.BaseFilter
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.UserCredentials
import java.util.function.Consumer

interface UserService {

    fun findByEmail(email: String): User?

    fun findByToken(token: String): User?

    fun findByFilter(filter: Filter): List<User>

    fun createUser(credentials: UserCredentials): User

    fun updateToken(user: User, token: String?)

    fun revokeToken(user: User)

    class Filter : BaseFilter() {

        var email: String? = null
        var token: String? = null

        companion object {
            /** Convenience method to build [Filter] */
            fun userFilter(f: Filter.() -> Unit) = Filter().apply(f)
            /** Convenience method to build [Filter] from Java */
            fun userFilter(f: Consumer<Filter>) = Filter().apply { f.accept(this) }
        }
    }
}