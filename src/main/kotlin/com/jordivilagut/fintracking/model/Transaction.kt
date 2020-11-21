package com.jordivilagut.fintracking.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("transactions")
class Transaction(
        @Id var id: ObjectId?,
        val userId: ObjectId,
        val date: Date,
        val amount: Number,
        val description: String)