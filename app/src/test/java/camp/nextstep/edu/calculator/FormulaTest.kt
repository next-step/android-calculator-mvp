package camp.nextstep.edu.calculator

import com.example.calculatorlib.Formula
import com.google.common.truth.Truth
import org.junit.Test

class FormulaTest{
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
        // givne: Formula클래스를 선언한다.
        val formula = Formula()

        // when : 첫 문자로 0~9 사이의 문자를 입력한다.
        formula.addNumber('1')

        // then : 문자열에 추가된다.
        Truth.assertThat(formula.getFormula()).isEqualTo("1")
    }

    @Test
    fun 마지막_문자가_숫자일_경우_숫자_입력_테스트() {
        // givne: 마지막 문자가 숫자인 Formula클래스를 선언한다.
        val formula = Formula("1")

        // when : 문자 0~9 사이의 문자를 입력한다.
        formula.addNumber('3')

        // then : 앞 숫자에 홥쳐진다. (13)
        Truth.assertThat(formula.getFormula()).isEqualTo("13")
    }

    @Test
    fun 마지막_문자가_연산자일_경우_숫자_입력_테스트() {
        // givne: 마지막 문자가 연산자인 Formula클래스를 선언한다.
        val formula = Formula("1 + 2 -")

        // when : 문자 0~9 사이의 문자를 입력한다.
        formula.addNumber('1')

        // then : 새로운 숫자가 입력된다.
        Truth.assertThat(formula.getFormula()).isEqualTo("1 + 2 - 1")
    }

    @Test
    fun 첫_입력일_경우_연산자_입력_테스트() {
        // givne: Formula클래스를 선언한다.
        val formula = Formula()

        // when : 첫 문자로 연산자 문자를 입력한다.
        formula.addOperator('+')

        // then : 문자열에 추가되지 않는다.
        Truth.assertThat(formula.getFormula()).isEqualTo("")
    }
    @Test
    fun 마지막_문자가_숫자일_경우_연산자_입력_테스트() {
        // givne: 마지막 문자가 숫자인 Formula클래스를 선언한다.
        val formula = Formula("1")

        // when : 연산자 문자를 입력한다.
        formula.addOperator('-')

        // then : 앞 문자열에 공백과 연산자가 추가된다. (1 -)
        Truth.assertThat(formula.getFormula()).isEqualTo("1 -")
    }

    @Test
    fun 마지막_문자가_연산자일_경우_연산자_입력_테스트() {
        // givne: 마지막 문자가 연산자인 Formula클래스를 선언한다.
        val formula = Formula("1 + 2 -")

        // when : 연산자 문자를 입력한다.
        formula.addOperator('/')

        // then : 직전 연산자가 입력된 연산자로 변경된다. (- -> /)
        Truth.assertThat(formula.getFormula()).isEqualTo("1 + 2 /")
    }

    @Test
    fun 마지막_문자가_숫자일_경우_직전값_숫자_포멧_확인() {
        // givne: 마지막 문자가 숫자인 Formula클래스를 선언한다.
        val formula = Formula("12")

        // when : 직전값이 숫자인지 검증해본다.
        val actual = formula.isLastStrNum()

        // then : true값을 반환한다.
        Truth.assertThat(actual).isEqualTo(true)
    }

    @Test
    fun 마지막_문자가_연산자일_경우_직전값_숫자_포멧_확인() {
        // givne: 마지막 문자가 연산자인 Formula클래스를 선언한다.
        val formula = Formula("12 +")

        // when : 직전값이 숫자인지 검증해본다.
        val actual = formula.isLastStrNum()

        // then : false값을 반환한다.
        Truth.assertThat(actual).isEqualTo(false)
    }

    @Test
    fun 첫_입력_없이_직전_값_삭제요청() {
        // givne: Formula클래스를 선언한다.
        val formula = Formula()

        // when : 직전 문자에 대한 삭제 요청을 한다.
        formula.deleteLastStr()

        // then : 아무 작업이 이루어 지지 않는다.
        Truth.assertThat(formula.getFormula()).isEqualTo("")
    }

    @Test
    fun 마지막_문자가_숫자일_경우_직전_값_삭제요청() {
        // givne: 마지막 문자가 숫자인 문자열 Formula클래스를 선언한다.
        val formula = Formula("12 + 22")

        // when : 직전 문자에 대한 삭제 요청을 한다.
        formula.deleteLastStr()

        // then : 직전 숫자가 삭제된다. (12 + 2)
        Truth.assertThat(formula.getFormula()).isEqualTo("12 + 2")
    }

    @Test
    fun 마지막_문자가_연산자일_경우_직전_값_삭제요청() {
        // givne: 마지막 문자가 연산자인 Formula클래스를 선언한다.
        val formula = Formula("12 + 22 *")

        // when : 직전 문자에 대한 삭제 요청을 한다.
        formula.deleteLastStr()

        // then : 직전 연산자가 삭제되고 공백도 삭제된다. (12 + 2)
        Truth.assertThat(formula.getFormula()).isEqualTo("12 + 22")
    }
}