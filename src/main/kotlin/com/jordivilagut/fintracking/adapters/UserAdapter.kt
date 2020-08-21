package com.jordivilagut.fintracking.adapters

import com.jordivilagut.fintracking.model.User

class UserAdapter {

    companion object {
        fun toUser(email: String, password: String): User {
            return User(
                    id = null,
                    email = email,
                    password = password,
                    token = null)
        }
    }
}