import com.google.common.truth.Truth.assertThat
import com.nextstep.calculator.Calculator
import org.junit.Test

/**
 * @author 박소연
 * @created 2023/02/05
 * @updated 2023/02/19
 * @desc 계산기 로직에 대한 테스트코드
 */

class CalculatorTest {
    @Test // 전체 수식 계산
    fun ` ' 2 + 3 ' 을 계산하고 5를 출력해야 한다`() {
        val calculator = Calculator()

        val actual = calculator.calculate("2 + 3")

        assertThat(actual).isEqualTo("5")
    }

    @Test // 두자리 수 수식 계산
    fun ` ' 12 + 3 ' 을 계산하고 15를 출력해야 한다`() {
        val calculator = Calculator()

        val actual = calculator.calculate("12 + 3")

        assertThat(actual).isEqualTo("15")
    }

    @Test // 전체 수식 계산
    fun ` ' 2 + 3 * 4 % 2 ' 을 계산하고 10을 출력해야 한다`() {
        val calculator = Calculator()

        val actual = calculator.calculate("2 + 3 * 4 / 2")

        assertThat(actual).isEqualTo("10")
    }

    @Test
    fun `공백 입력시 예외처리 해야한다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.calculate("")
        }.exceptionOrNull()

        if (actual?.message?.contains("입력값이 null이거나 빈 공백 문자") == true) {
            assertThat(actual).isNotNull()
        }
    }

    @Test
    fun `수식이 문자로 시작할 수 없다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.calculate("+ 2 * 3")
        }.exceptionOrNull()

        if (actual?.message?.contains("올바르지 않은 수식") == true) {
            assertThat(actual).isNotNull()
        }
    }

    @Test
    fun `수식이 문자로 끝날 수 없다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.calculate("2 * 3 +")
        }.exceptionOrNull()

        if (actual?.message?.contains("올바르지 않은 수식") == true) {
            assertThat(actual).isNotNull()
        }
    }

    @Test
    fun `수식에 문자가 중복될 수 없다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.calculate("2 * * 3")
        }.exceptionOrNull()

        if (actual?.message?.contains("올바르지 않은 수식") == true) {
            assertThat(actual).isNotNull()
        }
    }

    @Test
    fun `사칙연산자 외의 기호가 입력될 수 없다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.calculate("2 + 3 * 4 $ 2")
        }.exceptionOrNull()

        if (actual?.message?.contains("사칙연산자가 아닌 기호") == true) {
            assertThat(actual).isNotNull()
        }
    }

    @Test
    fun `0으로 나눌 수 없다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.calculate("2 / 0")
        }.exceptionOrNull()

        if (actual?.message?.contains("0으로 나눌 수 없다") == true) {
            assertThat(actual).isNotNull()
        }
    }
}
