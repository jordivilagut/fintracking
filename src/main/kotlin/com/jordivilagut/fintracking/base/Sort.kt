package com.jordivilagut.fintracking.base

class Sort(
        val field: String,
        val order: Int
) {

    companion object {
        const val ASC = 1
        const val DESC = -1
    }
}