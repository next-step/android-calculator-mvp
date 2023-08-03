package com.example.domain

import org.junit.Assert.*
import org.junit.Test
import com.example.domain.OperatorFinder.Companion.findOperator
import com.google.common.truth.Truth.assertThat
import java.lang.IllegalArgumentException

class OperatorTest {

    @Test
    fun 두_피연산자는_덧셈_연산이_가능하다() {
        val result = findOperator("+").calculate(Operand(1), Operand(2))

        assertThat(result).isEqualTo(Operand(3))
    }

    @Test
    fun 두_피연산자는_뺄셈_연산이_가능하다() {
        val result = findOperator("-").calculate(Operand(1), Operand(5))

        assertThat(result).isEqualTo(Operand(-4))
    }

    @Test
    fun 두_피연산자는_곱셈_연산이_가능하다() {
        val result = findOperator("*").calculate(Operand(3), Operand(2.5))

        assertThat(result).isEqualTo(Operand(7.5))
    }

    @Test
    fun 두_피연산자는_나눗셈_연산이_가능하다() {
        val result = findOperator("/").calculate(Operand(3), Operand(2))

        assertThat(result).isEqualTo(Operand(1.5))
    }

    @Test
    fun 숫자_0으로_나눗셈_연산은_불가능하다() {
        assertThrows(IllegalArgumentException::class.java) {
            findOperator("/").calculate(Operand(4), Operand(0))
        }
    }
}
