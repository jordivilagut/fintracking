package com.jordivilagut.fintracking.repositories

import com.jordivilagut.fintracking.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, ObjectId> {

    fun findByEmail(email: String): User?

    fun findByToken(token: String): User?
}