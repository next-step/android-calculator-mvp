package com.nextstep.edu.calculator.domain

class Calculator {

    /**
     * 사칙 연산을 모두 포함하는 기능
     *
     * @param operations 입력된 문자열
     * */
    fun evaluate(operations: String?): Int {
        require(!operations.isNullOrEmpty()) { "입력값이 존재하지 않습니다." }

        val operationList = operations.split(" ")

        require(isExactNumberExpression(operationList)) { "수식이 잘못되었습니다." }

        var result = operationList[0].toInt()

        for (i in 1 until operationList.size step 2) {
            val operator: Operator = Operator.find(operationList[i])
            val temp = OperationParse.parse(operationList[i + 1])
            result = operator.operate(result, temp)
        }

        return result
    }

    private fun isExactNumberExpression(operationList: List<String>): Boolean =
        (operationList.size % 2 != 0)
}