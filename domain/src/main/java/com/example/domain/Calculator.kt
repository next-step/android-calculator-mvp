package com.example.domain

class Calculator {
    fun evaluate(expressions: String?): Int {
        require(!expressions.isNullOrEmpty()) { "입력값이 존재하지 않습니다." }

        val expressionList = expressions.split(" ")
        require(expressionList.size % 2 == 1) { "불완전한 수식입니다." }

        var result = expressionList[0].toInt()

        for (i in 1 until expressionList.size step 2) {
            val operand = expressionList[i + 1].toIntOrNull()
            require(operand != null) { "피연산자는 숫자만 입력할 수 있습니다." }

            val op: Operator = when (expressionList[i]) {
                Operator.PLUS.op -> Operator.PLUS
                Operator.MINUS.op -> Operator.MINUS
                Operator.MULTIPLY.op -> Operator.MULTIPLY
                Operator.DIV.op -> Operator.DIV
                else -> throw IllegalArgumentException("지원하지 않는 사칙연산 기호입니다.")
            }

            result = op.operate(result, operand)
        }
        return result
    }
}