package com.example.domain

class Operand {
    companion object {
        fun of(op: String): Int {
            val validOperand = op.toIntOrNull()
            require(validOperand != null) { "피연산자는 숫자만 입력할 수 있습니다." }

            return validOperand
        }
    }
}