package com.example.domainlib

class Formula(private var formula: String = "") {

    fun getFormula() = formula

    // 숫자 추가
    fun addNumber(char: Char): String {
        if (formula.isEmpty()) {
            formula += char
            return formula
        }
        if (isLastStrNum()) {
            // 숫자 추가
            formula += char
        } else {
            // 스페이스 후 연산자 추가
            formula += " $char"
        }

        return formula
    }

    // 연산자 추가
    // 직전 값이 연산자일 경우 교체한다.
    fun addOperator(char: Char): String {
        if (formula.isEmpty()) return formula
        if (isLastStrNum()) {
            // 스페이스 후 숫자
            formula += " $char"
        } else {
            // 연산자
            formula = formula.dropLast(1)
            formula += char
        }

        return formula
    }

    // 직전 값이 숫자인가
    // return True: 숫자, False: 연산자
    // 직전 값이 없다면?
    fun isLastStrNum(): Boolean {
        val lastIndex = formula.length - 1
        return formula[lastIndex].isDigit()
    }

    // 최근 항목 삭제
    fun deleteLastStr(): String {
        if (formula.isEmpty()) return formula

        formula = formula.dropLast(1)
        formula = formula.trim()

        return formula
    }
}