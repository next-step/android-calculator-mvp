package com.example.domain

import java.lang.Integer.parseInt

@JvmInline
value class Operand(val value: Int) : OperationTerm {
    init {
        require(value > 0) {
            "음수를 지원 하지 않습니다."
        }
    }

    companion object {
        fun fromTerm(term: String): Operand {
            return Operand(parseInt(term))
        }
    }
}