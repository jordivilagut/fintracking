package com.jordivilagut.fintracking.utils

import org.apache.commons.lang3.RandomStringUtils

class PasswordUtils {

    companion object {
        fun generateRandomPassword(): String {
            val str = RandomStringUtils.randomAlphabetic(8)
            val number = RandomStringUtils.random(2, false, true)
            return str + number
        }
    }
}