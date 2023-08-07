package com.example.domain

import com.example.domain.OperatorFinder.Companion.findOperator

object Calculator {

    fun calculate(input: String): Operand {
        require(input.isNotBlank()) { "빈 공백은 입력할 수 없습니다." }

        val inputOperands = input.getListByDivRest(EVEN_NUMBER)
        val inputOperators = input.getListByDivRest(ODD_NUMBER)

        val operands = inputOperands.toOperandsList()
        val operators = inputOperators.toOperatorList()

        return operands.reduceIndexed { index, first, second ->
            operators[index - 1].calculate(first, second)
        }
    }

    private fun splitList(input: String): List<String> = input.split(SPACE)

    private fun String.getListByDivRest(rest: Int): List<String> = splitList(this).filterIndexed { index, _ ->
        index % 2 == rest
    }

    private fun List<String>.toOperandsList(): List<Operand> = this.map {
        Operand(it.toBigDecimal())
    }

    private fun List<String>.toOperatorList(): List<Operator> = this.map {
        findOperator(it)
    }
}

const val SPACE = " "
const val EVEN_NUMBER = 0
const val ODD_NUMBER = 1
