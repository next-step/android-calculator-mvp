package com.camp.nextstep.edu.domain


class Calculator {
    private val formulaRegex by lazy {
        Regex("""\s*([-+*/])\s*|\s*([\d.]+)\s*""")
    }

    private val validateConsecutiveCharRegex by lazy {
        Regex("\\b(?:\\d+\\s*\\+\\s*)+\\d+\\b|\\b(?:\\d+\\s*\\+\\+\\s*)+\\d+\\b|\\b(?:\\d+\\s*\\-\\s*)+\\d+\\b|\\b(?:\\d+\\s*\\*\\s*)+\\d+\\b|\\b(?:\\d+\\s*\\/\\s*)+\\d+\\b")
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

    private fun parseFormula(inputString: String): List<String> {
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

    private fun validate(list: List<String>) {
        require(Operator.isOperator(list.first()).not()) {
            "not valid formula"
        }
        require(Operator.isOperator(list.last()).not()) {
            "not valid formula"
        }

        list.joinToString()

        check(validateConsecutiveCharRegex.matches(
            list.joinToString(" ")
        )) {
            "not valid formula"
        }

    }



}