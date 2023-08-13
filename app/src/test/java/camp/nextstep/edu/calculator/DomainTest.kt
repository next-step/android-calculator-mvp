package camp.nextstep.edu.calculator

import com.example.calculatorlib.Calculator
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

class DomainTest {
    private lateinit var calculator: Calculator

    @Before
    fun initCalculator() {
        calculator = Calculator()
    }

    @Test
    fun 문자열_수식을_계산한다() {
        // when : calculator를 통해 수식을 계산한다.
        val actual = calculator.evaluate("1 + 2 + 3")

        // then : 사칙 연산의 우선 순위가 아닌 입력 우선 순위로 계산을 진행한다.
        assertThat(actual).isEqualTo(6)
    }

    @Test
    fun 사칙연산_우선순위와_상관없이_문자열의_순차적으로_수식을_계산한다() {
        // when : calculator를 통해 수식을 계산한다.
        val actual = calculator.evaluate("10 * 2 + 8 / 3")

        // then : 사칙 연산의 우선 순위가 아닌 입력 우선 순위로 계산을 진행한다.
        assertThat(actual).isEqualTo(9)
    }

    @Test
    fun 숫자구성이_아닌_값이_존재할_경우_에러를_발생시킨다() {
        // when : 수식을 공백 문자열로 채운다.
        val exception = runCatching { calculator.evaluate("3 * 2\n + 8 / 3\n") }.exceptionOrNull()

        // then : NumberFormatException이 발생한다.
        assertThat(exception).isInstanceOf(NumberFormatException::class.java)
    }

    @Test
    fun 사칙연산_기호가_아닌_값이_존재할_경우_에러를_발생시킨다() {
        // when : 사칙연산 기호가 아닌 값이 들어간다.
        val exception = runCatching { calculator.evaluate("1 - 2 ( 3 % 3") }.exceptionOrNull()


        // then : IllegalArgumentException이 발생한다.
        assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun 문자열_수식이_공백일_경우_에러를_발생시킨다() {
        // when : 수식을 공백 문자열로 채운다.
        val exception = runCatching { calculator.evaluate("       ") }.exceptionOrNull()

        // then : IllegalArgumentException이 발생한다.
        assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun 입력값이_NULL일_경우_에러를_발생시킨다() {
        // when : 입력값이 null이다.
        val exception = runCatching { calculator.evaluate(null) }.exceptionOrNull()

        // then : IllegalArgumentException이 발생한다.
        assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
    }
}

@RunWith(value = Parameterized::class)
class ManyCalculatorTest(private val input: String, private val expected: Int) {

    private lateinit var calculator: Calculator
    @Before
    fun initCalculator() {
        calculator = Calculator()
    }

    @Test
    fun 여러_문자열_수식을_계산한다() {
        // when : calculator를 통해 수식을 계산한다.
        val actual =  calculator.evaluate(input)
        // then : 사칙 연산의 우선 순위가 아닌 입력 우선 순위로 계산을 진행한다.
        assertThat(actual).isEqualTo(expected)
    }
    companion object {
        @JvmStatic
        @Parameters
        fun testData() = listOf(
            arrayOf("2 + 3", 5),
            arrayOf("3 * 2 - 1", 5),
            arrayOf("10 * 2 + 8 / 3", 9)
        )
    }
}

@RunWith(value = Parameterized::class)
class ManyCalculatorErrorTest(private val input: String?, private val expected: Exception) {

    private lateinit var calculator: Calculator
    @Before
    fun initCalculator() {
        calculator = Calculator()
    }


    @Test
    fun 여러_에러_케이스를_테스트한다() {
        // when : 에러가 발생하는 값들을 넣는다.
        val exception = runCatching { calculator.evaluate(input) }.exceptionOrNull()

        // then : IllegalArgumentException이 발생한다.
        assertThat(exception).isInstanceOf(expected::class.java)
    }

    companion object {
        @JvmStatic
        @Parameters
        fun testData() = listOf(
            arrayOf("1 - 2 ( 3 % 3", IllegalArgumentException()),
            arrayOf("       ", IllegalArgumentException()),
            arrayOf(null, IllegalArgumentException())
        )
    }
}














