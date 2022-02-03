package edu.nextstep.camp.calculator.domain

import java.lang.StringBuilder

data class History(
    val records: List<Record> = emptyList()
) {
    operator fun plus(record: Record): History {
        return History(records + record)
    }

    override fun toString(): String {
        val historyStringBuilder = StringBuilder()
        records.forEach {
            historyStringBuilder.append("${it.expression}\n")
            historyStringBuilder.append("${it.result}\n")
        }
        return historyStringBuilder.toString()
    }

    companion object {
        val EMPTY = History()
    }
}
