package com.camp.nextstep.edu.domain


object Operator {

    fun operator(operator: String, leftOperand: Int, rightOperand: Int): Int = when(operator) {
        "+" -> TokenType.Plus.operation(leftOperand, rightOperand)
        "-" -> TokenType.Minus.operation(leftOperand, rightOperand)
        "*" -> TokenType.Multiply.operation(leftOperand, rightOperand)
        "/" -> TokenType.Divide.operation(leftOperand, rightOperand)
        else -> throw UnsupportedOperationException("not support operator : $operator")
    }

    fun isOperator(operator: String) = when(operator) {
        TokenType.Plus.operator,
        TokenType.Minus.operator,
        TokenType.Multiply.operator,
        TokenType.Divide.operator -> true
        else -> false
    }

}