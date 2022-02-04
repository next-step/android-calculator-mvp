package edu.nextstep.camp.calculator.domain

class Histories(private val histories: MutableList<History> = mutableListOf()): MutableList<History> by histories {
    fun add(rawExpression: String, result: Int?) {
        histories.add(History(rawExpression, result))
    }
}
