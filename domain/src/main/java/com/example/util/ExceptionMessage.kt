package com.example.util

object ExceptionMessage {
    private const val ERROR = "[ERROR] "

    const val OPERATOR_NOT_EXIST = ERROR.plus("사칙연산 기호가 아닙니다.")
    const val OPERATOR_DIVIDE_NOT_ZERO = ERROR.plus("0으로 나눌 수 없습니다.")
    const val INCORRECT_EXPRESSION = ERROR.plus("연산할 수 있는 올바른 표현식이 아닙니다.")
}