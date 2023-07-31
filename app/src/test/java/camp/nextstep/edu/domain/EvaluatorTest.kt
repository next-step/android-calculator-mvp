package camp.nextstep.edu.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class EvaluatorTest {

    private val evaluator = Evaluator()

    @Test
    fun `1 더하기 3을 하면 4가 나온다`() {
        // given
        val firstNumber = 1
        val secondNumber = 3
        val operator = "+"

        // when
        val result = evaluator.evaluate("$firstNumber $operator $secondNumber")

        // then
        assertEquals(result, 4)
    }

    @Test
    fun `1 빼기 3을 하면 -2가 나온다`() {
        // given
        val firstNumber = 1
        val secondNumber = 3
        val operator = "-"

        // when
        val result = evaluator.evaluate("$firstNumber $operator $secondNumber")

        // then
        assertEquals(result, -2)
    }

    @Test
    fun `2 곱하기 3을 하면 6이 나온다`() {
        // given
        val firstNumber = 2
        val secondNumber = 3
        val operator = "×"

        // when
        val result = evaluator.evaluate("$firstNumber $operator $secondNumber")

        // then
        assertEquals(result, 6)
    }

    @Test
    fun `10 나누기 2을 하면 5가 나온다`() {
        // given
        val firstNumber = 10
        val secondNumber = 2
        val operator = "÷"

        // when
        val result = evaluator.evaluate("$firstNumber $operator $secondNumber")

        // then
        assertEquals(result, 5)
    }

    @Test
    fun `사칙연산 기호가 아닌 경우 IllegalArgumentException 이 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            val firstNumber = 10
            val secondNumber = 2
            val operator = "&"

            evaluator.evaluate("$firstNumber $operator $secondNumber")
        }
    }

    @Test
    fun `입력값이 공백인 경우 IllegalArgumentException 이 발생한다 - 1`() {
        assertThrows(IllegalArgumentException::class.java) {
            val firstNumber = ""
            val secondNumber = 2
            val operator = "+"

            evaluator.evaluate("$firstNumber $operator $secondNumber")
        }
    }

    @Test
    fun `입력값이 공백인 경우 IllegalArgumentException 이 발생한다 - 2`() {
        assertThrows(IllegalArgumentException::class.java) {
            val firstNumber = 10
            val secondNumber = ""
            val operator = "+"

            evaluator.evaluate("$firstNumber $operator $secondNumber")
        }
    }

    @Test
    fun `입력값이 공백인 경우 IllegalArgumentException 이 발생한다 - 3`() {
        assertThrows(IllegalArgumentException::class.java) {
            val firstNumber = 10
            val secondNumber = 2
            val operator = ""

            evaluator.evaluate("$firstNumber $operator $secondNumber")
        }
    }

    @Test
    fun `입력값이 null 인 경우 IllegalArgumentException 이 발생한다 - 1`() {
        assertThrows(IllegalArgumentException::class.java) {
            val firstNumber = null
            val secondNumber = 2
            val operator = "+"

            evaluator.evaluate("$firstNumber $operator $secondNumber")
        }
    }

    @Test
    fun `입력값이 null 인 경우 IllegalArgumentException 이 발생한다 - 2`() {
        assertThrows(IllegalArgumentException::class.java) {
            val firstNumber = 10
            val secondNumber = null
            val operator = "+"

            evaluator.evaluate("$firstNumber $operator $secondNumber")
        }
    }

    @Test
    fun `입력값이 null 인 경우 IllegalArgumentException 이 발생한다 - 3`() {
        assertThrows(IllegalArgumentException::class.java) {
            val firstNumber = 10
            val secondNumber = 2
            val operator = null

            evaluator.evaluate("$firstNumber $operator $secondNumber")
        }
    }

    @Test
    fun `1 더하기 2 곱하기 3 나누기 3 빼기 1 은 2이다`() {
        val result = evaluator.evaluate("1 + 2 × 3 ÷ 3 - 1")
        assertEquals(result, 2)
    }

    @Test
    fun `1 더하기 0 곱하기 1 나누기 1 빼기 -5 은 -4이다`() {
        val result = evaluator.evaluate("1 + 0 × 1 ÷ 1 - -5")
        assertEquals(result, 6)
    }

    @Test
    fun `5 더하기 0 더하기 0 더하기 7 빼기 3 곱하기 2 나누기 4 는 4 이다`() {
        val result = evaluator.evaluate("5 + 0 + 0 + 7 - 3 × 2 ÷ 4")
        assertEquals(result, 4)
    }

    @Test
    fun `0 더하기 -5 곱하기 -1 나누기 5 빼기 0 은 1 이다`() {
        val result = evaluator.evaluate("0 + -5 × -1 ÷ 5 - 0")
        assertEquals(result, 1)
    }
}