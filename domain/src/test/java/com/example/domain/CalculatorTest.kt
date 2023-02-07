package com.example.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CalculatorTest(
    private val input: String,
    private val result: Int
) {

    private val calculator = Calculator

    @Test
    fun 사칙연산_계산_테스트() {
        assertThat(calculator.calculate(input)).isEqualTo(Operand(result))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("1 + 3 + 5", 9),
            arrayOf("1 - 3 - 5", -7),
            arrayOf("10 + 30 + 60", 100),
            arrayOf("30 / 5 * 2", 12)
        )
    }
}

class CalculatorExceptionTest {
    private val calculator = Calculator

    @Test
    fun 빈공백_문자_입력하는_경우() {
        val result = calculator.calculate(" ")
        assertThat(result).isEqualTo(" ")
    }

    @Test
    fun 사칙연산_기호가_아닌_경우() {
        val result = calculator.calculate("3 # 5")
        assertThat(result).isEqualTo("")
    }

    @Test
    fun 사칙연산_기호가_아닌_경우2() {
        val result = calculator.calculate("10 & -1")
        assertThat(result).isEqualTo("")
    }

}
