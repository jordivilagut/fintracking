package com.jordivilagut.fintracking.utils

import kotlin.math.roundToInt

class MathUtil {

    companion object {
        fun round2Dec(number: Double) = (number * 100.0).roundToInt() /100.0

        fun negative(amount: Double) = 0 - amount
    }
}