package com.nextstep.edu.calculator.domain

data class Expression(
    private val operations: List<Any> = emptyList()
) {

    /**
     * Number 입력된 경우
     *
     * @param operand 입력된 숫자
     * */
    fun addOperand(operand: Int): Expression {
        return when (val last = operations.lastOrNull()) {
            is Operator -> Expression(operations + operand)
            is Int -> Expression(operations.dropLast(1) + "$last$operand".toInt())
            null -> Expression(listOf(operand))
            else -> throw IllegalArgumentException("Error 'addOperand' >> $last")
        }
    }

    /**
     * Operator 입력된 경우
     *
     * @param operator 입력된 Operator
     * */
    fun addOperator(`operator`: Operator): Expression {
        return when (val last = operations.lastOrNull()) {
            is Operator -> Expression(operations.dropLast(1) + `operator`)
            is Int, is String -> Expression(operations + `operator`)
            null -> EMPTY
            else -> throw IllegalArgumentException("Error 'addOperation' >> $last")
        }
    }

    /**
     * 지우기
     * */
    fun deleteOperations(): Expression {
        return when (val last = operations.lastOrNull()) {
            is Operator -> Expression(operations.dropLast(1))
            is Int -> {
                if (last / 10 == 0)
                    Expression(operations.dropLast(1))
                else
                    Expression(operations.dropLast(1) + listOfNotNull(last / 10))
            }
            null -> EMPTY
            else -> throw IllegalArgumentException("Error 'deleteOperations' >> $last")

        }
    }

    override fun toString(): String {
        return operations.joinToString(separator = " ") {
            if (it is Operator) {
                it.symbol
            } else {
                "$it"
            }
        }
    }

    companion object {
        val EMPTY = Expression()
    }
}