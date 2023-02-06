package com.nextstep.edu.calculator.domain

object OperationsUtil {
    /**
     * Number 입력된 경우
     *
     * @param operations 현재 수식
     * @param inputNumberString 입력된 숫자
     * */
    fun setOperationsNumber(operations: String, inputNumberString: String): String {
        val number = OperationParse.parse(inputNumberString)
        return operations + number.toString()
    }

    /**
     * Operator 입력된 경우
     *
     * @param operations 현재 수식
     * @param inputOperatorString 입력된 Operator
     * */
    fun setOperationsOperator(operations: String, inputOperatorString: String): String {
        if (operations.isEmpty()) return ""

        val symbol = Operator.find(inputOperatorString).symbol

        // Int 로 형 변환이 될 때까지 drop
        return operations.dropLastWhile { it.digitToIntOrNull() == null } + " $symbol "
    }

    /**
     * 지우기
     *
     * @param operations 현재 수식
     * */
    fun deleteOperations(operations: String): String {
        if (operations.isEmpty()) return ""

        return if (isStringLastIndexOperator(operations)) {
            operations.dropLast(3)
        } else {
            operations.dropLast(1)
        }
    }

    /**
     * 연산의 마지막 문자가 Operator 인지 체크
     *
     * @param operations 현재 수식
     *
     * @return 마지막 문자가 Operator ?
     * */
    private fun isStringLastIndexOperator(operations: String): Boolean {
        return try {
            Operator.find(operations.trim().last().toString())
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}