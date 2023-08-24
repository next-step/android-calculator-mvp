package com.example.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CalculatorTest(
    private val input: String,
    private val result: Int
) {

    private val calculator = Calculator
    private val expression = Expression

    private fun addToInput(input: String): Result<Unit> {
        return when (expression.lastInputState) {
            InputState.Init -> {
                expression.currentInitStateInput(input)
            }

            InputState.Operand -> {
                expression.currentOperandStateInput(input)
            }

            InputState.Operator -> {
                expression.currentOperatorStateInput(input)
            }

            else -> Result.failure(IllegalArgumentException("오류가 발생했습니다. 계산기를 초기화해주세요."))
        }
    }

    @Before
    fun init() {
        expression.clearCurrentOperandList()
    }

    // then
    @Test
    fun 사칙연산_계산_테스트() {
        input.split(" ").forEach {
            addToInput(it)
        }
        val actual = calculator.calculate(input)
        assertThat(actual).isEqualTo(Operand(result))
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
        assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate(" ")
        }
    }

    @Test
    fun 사칙연산_기호가_아닌_경우() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate("3 # 5")
        }
    }

    @Test
    fun 사칙연산_기호가_아닌_경우2() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate("10 & -1")
        }
    }

}
