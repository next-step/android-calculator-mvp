import com.google.common.truth.Truth.assertThat
import com.nextstep.calculator.Calculator
import org.junit.Test

/**
 * @author 박소연
 * @created 2023/02/05
 * @updated 2023/02/06
 * @desc
 */

class CalculatorTest {
    @Test // 전체 수식 계산
    fun test1_calculate() {
        val calculator = Calculator()
        calculator.calculate("2+3*4/2")
        val actual: Int? = calculator.getResult()
        assertThat(actual).isEqualTo(10)
    }

    @Test(expected = IllegalArgumentException::class) // 공백 입력
    fun test2_inputNull() {
        val calculator = Calculator()
        calculator.calculate("")
        val actual: Int? = calculator.getResult()
        assertThat(actual).isEqualTo(null)
    }

    @Test(expected = IllegalArgumentException::class) // 정의되지 않은 기호 입력
    fun test3_inputUndefinedOperator() {
        val calculator = Calculator()
        calculator.calculate("2+3*4$2")
        val actual: Int? = calculator.getResult()
        assertThat(actual).isEqualTo(null)
    }

    @Test(expected = ArithmeticException::class) // 정의되지 않은 기호 입력
    fun test4_divideByZero() {
        val calculator = Calculator()
        calculator.calculate("2/0")
    }
}
