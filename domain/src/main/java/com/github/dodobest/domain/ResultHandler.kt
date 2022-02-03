package com.github.dodobest.domain

class ResultHandler {
    private val results: ArrayList<Result> = arrayListOf()

    fun add(expression: String, result: String) {
        results.add(Result(expression, result))
    }

    fun getResults(): ArrayList<Result> {
        return results
    }
}