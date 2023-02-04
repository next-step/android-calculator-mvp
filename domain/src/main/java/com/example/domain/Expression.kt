package com.example.domain

import com.example.util.ExceptionMessage.INCORRECT_EXPRESSION

class Expression {
    private var values: String = ""

    fun append(operator: String) {
        if (values.isEmpty()) throw IllegalArgumentException(INCORRECT_EXPRESSION)

        values = values.plus(EMPTY).plus(operator)
    }

    fun append(number: Int) {
        if (values.isNotEmpty())
            values = values.plus(EMPTY)

        values = values.plus(number)
    }

    fun value(): String {
        return values
    }

    companion object {
        const val EMPTY = " "
    }
}