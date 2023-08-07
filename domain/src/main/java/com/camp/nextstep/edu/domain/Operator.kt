package com.camp.nextstep.edu.domain

import com.camp.nextstep.edu.domain.TokenType.*

class Operator {
    companion object {
        fun isOperator(operator: String) = when(operator) {
            Plus.operator,
            Minus.operator,
            Multiply.operator,
            Divide.operator -> true
            else -> false
        }

        fun operator(operator: String, leftOperand: Int, rightOperand: Int): Int = when(operator) {
            Plus.operator -> plus(leftOperand, rightOperand)
            Minus.operator -> minus(leftOperand, rightOperand)
            Multiply.operator -> multiply(leftOperand, rightOperand)
            Divide.operator -> divide(leftOperand, rightOperand)
            else -> throw UnsupportedOperationException("not support operator : $operator")
        }

        fun plus(leftOperand: Int, rightOperand: Int): Int = (leftOperand + rightOperand)
        fun minus(leftOperand: Int, rightOperand: Int): Int = (leftOperand - rightOperand)
        fun multiply(leftOperand: Int, rightOperand: Int): Int = (leftOperand * rightOperand)
        fun divide(leftOperand: Int, rightOperand: Int): Int = (leftOperand / rightOperand)
    }
}