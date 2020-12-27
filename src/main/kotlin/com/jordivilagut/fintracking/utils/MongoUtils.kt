package com.jordivilagut.fintracking.utils

import org.bson.types.ObjectId

class MongoUtils {

    companion object {
        fun toId(id: String): ObjectId {
            return ObjectId(id)
        }
    }
}