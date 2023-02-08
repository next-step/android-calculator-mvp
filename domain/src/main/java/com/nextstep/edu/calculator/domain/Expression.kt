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
            is Operator -> Expression(operations + last)
            is Int -> Expression(operations.dropLast(1) + "$last$operand")
            null -> EMPTY
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
            is Operator -> Expression(operations.dropLast(1) + last)
            is Int -> Expression(operations + last)
            null -> EMPTY
            else -> throw IllegalArgumentException("Error 'addOperation' >> $last")
        }
    }

    /**
     * 지우기
     *
     * @param operations 현재 수식
     * */
    fun deleteOperations(): Expression {
        return when (val last = operations.lastOrNull()) {
            is Operator -> Expression(operations.dropLast(1))
            is Int -> {
                val operand = if (last / 10 == 0) last else last / 10
                Expression(operations.dropLast(1) + listOfNotNull(operand))
                EMPTY
            }
            null -> EMPTY
            else -> throw IllegalArgumentException("Error 'deleteOperations' >> $last")

        }
    }

    override fun toString(): String {
        return operations.joinToString(separator = " ") {
            return@joinToString if (it is Operator) {
                " $it "
            } else {
                "$it"
            }
        }
    }

    companion object {
        val EMPTY = Expression()
    }
}