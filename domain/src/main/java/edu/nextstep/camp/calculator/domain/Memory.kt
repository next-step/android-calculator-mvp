package edu.nextstep.camp.calculator.domain

import java.lang.StringBuilder

class Memory {
    private val memoryBuilder = StringBuilder()

    fun add(expression: Expression, result: Int) {
        if (memoryBuilder.isNotEmpty()) {
            memoryBuilder.appendLine().appendLine()
        }

        memoryBuilder.append("$expression")
            .appendLine()
            .append("= $result")
    }

    override fun toString(): String {
        return memoryBuilder.toString()
    }
}