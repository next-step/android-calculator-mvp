package camp.nextstep.edu.calculator

import com.example.calculatorlib.Calculator
import com.example.calculatorlib.Formula
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

    private var numAndOperator = "12 +"

    @Test
    fun 마지막_값을_검증한다() {
        // given: 문자열의 마지막 인덱스를 구한다.
        val lastIndex = numAndOperator.length - 1

        // when: 문자열의 마지막 값이 숫자인지 확인합니다.
        val actual = numAndOperator[lastIndex].isDigit()

        // then: 숫자일 경우 true, 숫자 이외의 것 이라면 false
        assertThat(actual).isEqualTo(false)
    }

    @Test
    fun 마지막으로_입력된_연산자를_교체한다() {
        // given: 문자열의 마지막 인덱스를 구한다.
        val lastIndex = numAndOperator.length - 1

        // when: 마지막 문자열의 값을 삭제 후 추가한다.
        numAndOperator = numAndOperator.dropLast(1)
        numAndOperator += "*"
        val actual = numAndOperator[lastIndex]

        // then: 숫자일 경우 true, 숫자 이외의 것 이라면 false
        assertThat(actual).isEqualTo('*')
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

class FormulaTest {
    var formula: Formula = Formula()

    /**
     * 1. 숫자 추가
     *  - 첫 입력일 경우 0~9 정상, 0 반응X
     *  - 직전 값 숫자인 경우 0~9 정상
     *  - 직전 값 연산자인 경우 0~9 정상
     * 2. 연산자 추가
     *  - 첫 입력일 경우 반응 X
     *  - 직전 값 숫자인 경우 정상 입력
     *  - 직전 값이 연산자인 경우 연산자 변경
     * 3. 직전 값 포맷 확인(숫자 or 연산자)
     * 4. 최근 항목 삭제
     *  - 첫 입력일 경우 반응 X
     *  - 직전 값 숫자인 경우 숫자 삭제
     *  - 직전 값이 연산자인 경우 연산자 삭제
     * */

    @Test
    fun 첫_입력일_경우_숫자_입력_테스트() {
        // when : 첫 문자로 0~9 사이의 문자를 입력한다.
        formula.addNumber('1')

        // then : 문자열에 추가된다.
        assertThat(formula.getFormula()).isEqualTo("1")
    }

    @Test
    fun 직전문자가_숫자일_경우_숫자_입력_테스트() {
        // given : 직전 문자가 숫자이다.
        formula.addNumber('1')

        // when : 문자 0~9 사이의 문자를 입력한다.
        formula.addNumber('3')

        // then : 앞 숫자에 홥쳐진다. (13)
        assertThat(formula.getFormula()).isEqualTo("13")
    }

    @Test
    fun 직전문자가_연산자일_경우_숫자_입력_테스트() {
        // given : 직전 문자가 숫자이다.
        formula.setFormula("1 + 2 -")

        // when : 문자 0~9 사이의 문자를 입력한다.
        formula.addNumber('1')

        // then : 새로운 숫자가 입력된다.
        assertThat(formula.getFormula()).isEqualTo("1 + 2 - 1")
    }

    @Test
    fun 첫_입력일_경우_연산자_입력_테스트() {
        // when : 첫 문자로 연산자 문자를 입력한다.
        formula.addOperator('+')

        // then : 문자열에 추가되지 않는다.
        assertThat(formula.getFormula()).isEqualTo("")
    }
    @Test
    fun 직전문자가_숫자일_경우_연산자_입력_테스트() {
        // given : 직전 문자가 숫자이다.
        formula.addNumber('1')

        // when : 연산자 문자를 입력한다.
        formula.addOperator('-')

        // then : 앞 문자열에 공백과 연산자가 추가된다. (1 -)
        assertThat(formula.getFormula()).isEqualTo("1 -")
    }

    @Test
    fun 직전문자가_연산자일_경우_연산자_입력_테스트() {
        // given : 직전 문자가 연산자이다.
        formula.setFormula("1 + 2 -")

        // when : 연산자 문자를 입력한다.
        formula.addOperator('/')

        // then : 직전 연산자가 입력된 연산자로 변경된다. (- -> /)
        assertThat(formula.getFormula()).isEqualTo("1 + 2 /")
    }

    @Test
    fun 직전_값이_숫자일_경우_직전값_숫자_포멧_확인() {
        // given : 직전 값이 숫자이다.
        formula.setFormula("12")

        // when : 직전값이 숫자인지 검증해본다.
        val actual = formula.isLastStrNum()

        // then : true값을 반환한다.
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun 직전_값이_연산자일_경우_직전값_숫자_포멧_확인() {
        // given : 직전 값이 숫자이다.
        formula.setFormula("12 +")

        // when : 직전값이 숫자인지 검증해본다.
        val actual = formula.isLastStrNum()

        // then : true값을 반환한다.
        assertThat(actual).isEqualTo(false)
    }

    @Test
    fun 첫_입력_없이_직전_값_삭제요청() {
        // given : 입력값이 존재하지 않는다.

        // when : 직전 문자에 대한 삭제 요청을 한다.
        formula.deleteLastStr()

        // then : 아무 작업이 이루어 지지 않는다.
        assertThat(formula.getFormula()).isEqualTo("")
    }

    @Test
    fun 직전_문자가_숫자일_경우_직전_값_삭제요청() {
        // given : 마지막 문자가 숫자인 문자열을 입력한다.
        formula.setFormula("12 + 22")

        // when : 직전 문자에 대한 삭제 요청을 한다.
        formula.deleteLastStr()

        // then : 직전 숫자가 삭제된다. (12 + 2)
        assertThat(formula.getFormula()).isEqualTo("12 + 2")
    }

    @Test
    fun 직전_문자가_연산자일_경우_직전_값_삭제요청() {
        // given : 마지막 문자가 숫자인 문자열을 입력한다.
        formula.setFormula("12 + 22 *")

        // when : 직전 문자에 대한 삭제 요청을 한다.
        formula.deleteLastStr()

        // then : 직전 연산자가 삭제되고 공백도 삭제된다. (12 + 2)
        assertThat(formula.getFormula()).isEqualTo("12 + 22")
    }
}














