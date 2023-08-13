package camp.nextstep.edu.calculator

import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class PresenterMockTest {
    private val view: Contract.View = mockk(relaxed = true)
    private val presenter = Presenter(view)

    @Test
    fun `Presenter 인스턴스 생성`() {
        // when :

        // then :
        assertThat(presenter).isNotNull()
    }

    @Test
    fun `수식 문자열에 숫자를 추가한다`() {
        // when : 숫자를 추가한다.
        presenter.addNumber('2')

        // then : 수식 문자열에 숫자가 추가된다.
        val expected = "2"
        verify { view.showFormula(expected) }
    }

    @Test
    fun `직전 문자가 숫자인 수식 문자열에 숫자를 입력한다`() {
       // give : 직전 문자를 숫자로 세팅한다
        presenter.addNumber('2')

        // when : 숫자를 추가한다.
        presenter.addNumber('4')

        // then : 수식 문자열에 숫자가 추가된다.
        val expected = "24"
        verify { view.showFormula(expected) }
    }

    @Test
    fun `직전 문자가 숫자인 수식 문자열에 연산자를 입력한다`() {
        // give : 직전 문자를 숫자로 세팅한다
        presenter.addNumber('2')

        // when : 숫자를 추가한다.
        presenter.addOperator('+')

        // then : 수식 문자열에 공백과 연산자가 추가된다.
        val expected = "2 +"
        verify { view.showFormula(expected) }
    }

    @Test
    fun `직전 문자가 연산자인 수식 문자열에 숫자를 입력한다`() {
        // give : 직전 문자를 연산자로 세팅한다
        presenter.addNumber('2')
        presenter.addOperator('+')

        // when : 숫자를 추가한다.
        presenter.addNumber('4')

        // then : 수식 문자열에 공백과 숫자가 추가된다.
        val expected = "2 + 4"
        verify { view.showFormula(expected) }
    }

    @Test
    fun `직전 문자가 연산자인 수식 문자열에 연산자를 입력한다`() {
        // give : 직전 문자를 연산자로 세팅한다
        presenter.addNumber('2')
        presenter.addOperator('+')

        // when : 숫자를 추가한다.
        presenter.addOperator('/')

        // then : 직전 문자인 연산자가 새로운 값으로 대체된다.
        val expected = "2 /"
        verify { view.showFormula(expected) }
    }

    @Test
    fun `수식의 마지막 문자를 삭제한다`() {
        // give : 샘플 수식을 세팅한다.
        presenter.addNumber('2')
        presenter.addOperator('+')

        // when : 수식 문자열에의 최근 문자를 삭제 한다.
        presenter.deleteLastStr()

        // then : 직전 문자가 삭제된다.
        val expected = "2"
        verify { view.showFormula(expected) }
    }

    @Test
    fun `수식을 계산한다`() {
        // give : 샘플 수식을 세팅한다.
        presenter.addNumber('2')
        presenter.addOperator('+')
        presenter.addNumber('2')

        // when : 수식을 계산합니다.
        presenter.calculate()

        // then : 수식의 결과값을 보여줍니다.
        val expected = "4"
        verify { view.showResult(expected) }
    }

    @Test
    fun `완성된 수식이 아닐 경우 계산 요청`() {
        // given : 불완전한 수식을 세팅한다.
        presenter.addNumber('2')
        presenter.addOperator('+')

        // when : 수식을 계산합니다.
        presenter.calculate()

        // then : 완성되지 않은 수식입니다 노출
        val expected = "완성되지 않은 수식입니다"
        verify { view.showToast(expected) }
    }
}