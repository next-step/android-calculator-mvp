package com.github.dodobest.domain

class ResultHandler {
    private val resultList: ArrayList<Result> = arrayListOf()

    fun add(expression: String, result: String) {
        resultList.add(Result(expression, result))
    }

    fun getResultList(): ArrayList<Result> {
        return resultList
    }
}