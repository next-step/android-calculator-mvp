package com.camp.nextstep.edu.domain

import com.camp.nextstep.edu.domain.TokenType.*

class Operator {
    companion object {
        private val formulaRegex by lazy {
            Regex("""\s*([-+*/])\s*|\s*([\d.]+)\s*""")
        }
        fun parseFormula(inputString: String): List<String> {
            val tokens = mutableListOf<String>()
            formulaRegex.findAll(inputString).forEach { matchResult ->
                val (operator, operand) = matchResult.destructured
                if (operator.isNotEmpty()) {
                    tokens.add(operator)
                }
                if (operand.isNotEmpty()) {
                    tokens.add(operand)
                }
            }
            return tokens
        }

        fun operator(operator: String, leftOperand: Int, rightOperand: Int): Int = when(operator) {
            Plus.operator -> plus(leftOperand, rightOperand)
            Minus.operator -> minus(leftOperand, rightOperand)
            Multiply.operator -> multiply(leftOperand, rightOperand)
            Divide.operator -> divide(leftOperand, rightOperand)
            else -> throw UnsupportedOperationException("not support operator : $operator")
        }

        fun isOperator(operator: String) = when(operator) {
            Plus.operator,
            Minus.operator,
            Multiply.operator,
            Divide.operator -> true
            else -> false
        }
        fun plus(leftOperand: Int, rightOperand: Int): Int = (leftOperand + rightOperand)
        fun minus(leftOperand: Int, rightOperand: Int): Int = (leftOperand - rightOperand)
        fun multiply(leftOperand: Int, rightOperand: Int): Int = (leftOperand * rightOperand)
        fun divide(leftOperand: Int, rightOperand: Int): Int = (leftOperand / rightOperand)
    }
}