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
    fun ` ' 2 + 3 ' 을 계산하고 5를 출력해야 한다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.checkExpression("2+3")
        }.onFailure { e ->
            e.printStackTrace()
            throw IllegalArgumentException()
        }.getOrNull()

        assertThat(actual).isEqualTo(5)
    }

    @Test // 전체 수식 계산
    fun ` ' 2 + 3 * 4 % 2 ' 을 계산하고 10을 출력해야 한다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.calculate("2+3*4/2")
        }.onFailure { e ->
            e.printStackTrace()
            throw IllegalArgumentException()
        }.getOrNull()

        assertThat(actual).isEqualTo(10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `공백 입력시 null을 출력해야 한다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.checkExpression("")
        }.onFailure { e ->
            e.printStackTrace()
            throw IllegalArgumentException()
        }.exceptionOrNull()

        assertThat(actual).isNotNull()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `문자로 시작하는 수식이 입력되면 예외처리 해야한다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.checkExpression("+2*3")
        }.onFailure { e ->
            e.printStackTrace()
            throw IllegalArgumentException()
        }.exceptionOrNull()

        assertThat(actual).isNotNull()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `문자로 끝나는 수식이 입력되면 예외처리 해야한다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.checkExpression("2*3+")
        }.onFailure { e ->
            e.printStackTrace()
            throw IllegalArgumentException()
        }.exceptionOrNull()

        assertThat(actual).isNotNull()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `수식에 문자가 중복되어 나오면 예외처리 해야한다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.checkExpression("2**3")
        }.onFailure { e ->
            e.printStackTrace()
            throw IllegalArgumentException()
        }.exceptionOrNull()

        assertThat(actual).isNotNull()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `사칙연산자 외의 기호가 입력되면 null을 출력해야 한다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.checkExpression("2+3*4$2")
        }.onFailure { e ->
            e.printStackTrace()
            throw IllegalArgumentException()
        }.exceptionOrNull()

        assertThat(actual).isNotNull()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `0으로 나누면 에러를 반환한다`() {
        val calculator = Calculator()

        val actual = runCatching {
            calculator.checkExpression("2/0")
        }.onFailure { e ->
            e.printStackTrace()
            throw IllegalArgumentException()
        }.exceptionOrNull()

        assertThat(actual).isNotNull()
    }
}
