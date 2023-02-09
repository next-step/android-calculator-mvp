package com.nextstep.edu.calculator.domain

object OperationParse {
    fun parse(operation: String): Int {
        val parseNumber = operation.toIntOrNull()

        requireNotNull(parseNumber) { "수식이 잘못되었습니다." }
        require(!isNegativeNumber(parseNumber)) { "음수를 입력하실 수 없습니다." }

        return parseNumber
    }


    private fun isNegativeNumber(number: Int): Boolean = number < 0
}