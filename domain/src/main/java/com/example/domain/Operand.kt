package com.example.domain

import java.lang.Integer.parseInt

@JvmInline
value class Operand(val value: Int) : OperationTerm {
    init {
        require(value >= 0) {
            "음수를 지원 하지 않습니다."
        }
    }

    companion object {
        fun fromTerm(term: String): Operand {
            val value = term.toIntOrNull()
            require(value != null) {
                "숫자로 변환 불가능한 문자입니다."
            }
            return Operand(value)
        }
    }
}