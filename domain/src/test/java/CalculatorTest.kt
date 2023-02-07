import com.google.common.truth.Truth.assertThat
import com.nextstep.calculator.Calculator
import org.junit.Test

/**
 * @author 박소연
 * @created 2023/02/05
 * @updated 2023/02/08
 * @desc 계산기 로직에 대한 테스트코드
 */

class CalculatorTest {
    @Test // 전체 수식 계산
    fun ` ' 2 + 3 * 4 % 2 ' 을 계산하고 10을 출력해야 한다`() {
        val calculator = Calculator()
        val actual: Int = calculator.calculate("2+3*4/2")
        assertThat(actual).isEqualTo(10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `공백 입력시 null을 출력해야 한다`() {
        val calculator = Calculator()
        val actual: Int = calculator.calculate("")
        assertThat(actual).isEqualTo(null)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `사칙연산자 외의 기호가 입력되면 null을 출력해야 한다`() {
        val calculator = Calculator()
        val actual: Int = calculator.calculate("2+3*4$2")
        assertThat(actual).isEqualTo(null)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `0으로 나누면 에러를 반환한다`() {
        val calculator = Calculator()
        calculator.calculate("2/0")
    }
}
