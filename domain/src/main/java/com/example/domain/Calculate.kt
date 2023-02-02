package com.example.domain

class Calculator(private val operationParser: OperationParser = OperationParser()) {
    fun calculate(operation: String): Int {

        val terms = operationParser.parse(operation)

        var accumulator: Int = (terms[0] as Operand).value
        for (index: Int in 1 until terms.size step 2) {
            val operator: Operator = terms[index] as Operator
            val operand = terms[index + 1]

            accumulator = operator.execute(accumulator, (operand as Operand).value)
        }
        return accumulator
    }
}