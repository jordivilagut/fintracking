package com.jordivilagut.fintracking.base

class Path(private val uri: String) {

    fun get(): String {
        return "/$uri"
    }

    fun allChildren(): String {
        return "/$uri/**"
    }
}