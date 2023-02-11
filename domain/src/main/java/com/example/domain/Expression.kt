package com.example.domain

data class Expression(val expressions: List<Any> = emptyList()) {
    private val expressionList = expressions.toMutableList()

    fun appendOperator(operator: Operator) {
        when (expressionList.lastOrNull()) {
            is Operator -> {
                expressionList.removeLast()
                expressionList.add(operator)
            }

            is Int -> {
                expressionList.add(operator)
            }
        }
    }

    fun appendOperand(operand: Int) {
        when (val lastExpression = expressionList.lastOrNull()) {
            is Operator -> {
                expressionList.add(operand)
            }

            null -> {
                expressionList.add(operand)
            }

            is Int -> {
                expressionList.removeLast()
                expressionList.add(lastExpression * 10 + operand)
            }
        }
    }

    fun removeLastValue() {
        when (val lastExpression = expressionList.lastOrNull()) {
            is Operator -> {
                expressionList.removeLast()
            }

            is Int -> {
                removeUnits(lastExpression)
            }
        }
    }

    private fun removeUnits(lastExpression: Int) {
        if (lastExpression < 10) {
            expressionList.removeLast()
        } else {
            expressionList.removeLast()
            expressionList.add(lastExpression / 10)
        }
    }
}