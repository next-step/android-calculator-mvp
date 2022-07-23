package edu.nextstep.camp.calculator.domain

interface CalculatorHistorySaver {
    fun save(rawExpression: String, rawResult: String)
    fun loadAll(): String
}