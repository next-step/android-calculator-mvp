package edu.nextstep.camp.calculator.domain

data class HistoryList(
    private val historyList: List<History> = listOf()
) {

    operator fun plus(other: History) = HistoryList(historyList + other)
}
