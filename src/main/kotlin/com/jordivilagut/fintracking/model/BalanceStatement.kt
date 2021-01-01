package com.jordivilagut.fintracking.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("balanceStatements")
class BalanceStatement(
    @Id var id: ObjectId?,
    val userId: ObjectId,
    val date: Date,
    val balance: Double)