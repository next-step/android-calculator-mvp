package com.example.domain

import com.example.util.ExceptionMessage.OPERATOR_DIVIDE_NOT_ZERO
import com.example.util.ExceptionMessage.OPERATOR_NOT_EXIST

enum class Operator(private val operator: String) {
    PLUS("+"),
    MINUS("-"),
    MULTI("*"),
    DIVIDE("/"),;


    companion object {
        fun valueOf(valueA: Int, valueB: Int, operator: String) : Int {
            return when(operator) {
                PLUS.operator -> valueA + valueB
                MINUS.operator -> valueA - valueB
                MULTI.operator -> valueA * valueB
                DIVIDE.operator -> {
                    if (valueB == 0)
                        throw IllegalArgumentException(OPERATOR_DIVIDE_NOT_ZERO)

                    valueA / valueB
                }
                else -> throw IllegalArgumentException(OPERATOR_NOT_EXIST)
            }
        }
    }
}