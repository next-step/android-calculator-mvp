package com.camp.nextstep.edu.domain

import com.camp.nextstep.edu.domain.Operator.TokenType
import com.camp.nextstep.edu.domain.Operator.TokenType.*

class Calculator {

    private fun parseFormula(inputString: String): List<String> {
        val regex = Regex("""\s*([-+*/])\s*|\s*([\d.]+)\s*""")
        val tokens = mutableListOf<String>()

        regex.findAll(inputString).forEach { matchResult ->
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

    private fun validate(list: List<String>) {
        check(Operator.isOperator(list.first()).not()) {
            throw IllegalArgumentException("not valid formula")
        }
        check(Operator.isOperator(list.last()).not()) {
            throw IllegalArgumentException("not valid formula")
        }

        val regex = Regex("(.)\\1+")
        check(regex.matches(
            list.toString().removePrefix("[").removeSuffix("]").replace(",", " ")
        )) {
            throw IllegalArgumentException("not valid formula")
        }

    }

    fun evaluate(inputString: String): Int {
        check(inputString.isNotEmpty()) {
            "string is empty"
        }

        val formulaList = parseFormula(inputString).toMutableList()

        validate(formulaList)

        val recursiveCount = formulaList.size / 2
        for (i: Int in 0 .. recursiveCount) {
            if (1 == formulaList.size) break

            val leftOperand = formulaList[0].toInt()
            val operator = formulaList[1]
            val rightOperand = formulaList[2].toInt()

            val result = Operator.operator(operator, leftOperand, rightOperand)
            repeat(3) {
                formulaList.removeFirst()
            }
            formulaList.add(0, "$result")
        }

        return formulaList.last().toInt()
    }

}