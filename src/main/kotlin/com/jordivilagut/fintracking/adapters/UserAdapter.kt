package com.jordivilagut.fintracking.adapters

import com.jordivilagut.fintracking.model.User

class UserAdapter {

    companion object {
        fun toUser(name: String, email: String, password: String): User {
            return User(
                    id = null,
                    name = name,
                    email = email,
                    password = password,
                    token = null)
        }
    }
}