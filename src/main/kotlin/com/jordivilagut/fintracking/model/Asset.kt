package com.jordivilagut.fintracking.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("assets")
class Asset(
    @Id var id: ObjectId?,
    val userId: ObjectId,
    val name: String,
    val platformName: String,
    val type: AssetType,
    val created: Date,
    val updated: Date,
    val investedAmount: Double,
    val currentAmount: Double,
    val taxes: List<Tax>)