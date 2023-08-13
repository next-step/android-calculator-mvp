package camp.nextstep.edu.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PresenterTest {

    @Test
    fun `수식 문자열에 숫자를 추가한다`() {
        // given : Presenter를 생성한다.
        var actual = ""
        val view = createView(
            onShowFormula = { str -> actual = str},
            onShowResult = { str -> actual = str},
            onShowToast = { str -> actual = str}
        )
        val presenter = Presenter(view)

        // when : 숫자를 추가한다.
        presenter.addNumber('2')

        // then : 수식 문자열에 숫자가 추가된다.
        assertThat(actual).isEqualTo("2")
    }

    @Test
    fun `직전 문자가 숫자인 수식 문자열에 숫자를 입력한다`() {
        // given : Presenter를 생성하고 숫자를 추가한다.
        var actual = ""
        val view = createView(
            onShowFormula = { str -> actual = str},
            onShowResult = { str -> actual = str},
            onShowToast = { str -> actual = str}
        )
        val presenter = Presenter(view)
        presenter.addNumber('2')

        // when : 숫자를 추가한다.
        presenter.addNumber('4')

        // then : 수식 문자열에 숫자가 추가된다.
        assertThat(actual).isEqualTo("24")
    }

    @Test
    fun `직전 문자가 숫자인 수식 문자열에 연산자를 입력한다`() {
        // given : Presenter를 생성하고 숫자를 추가한다.
        var actual = ""
        val view = createView(
            onShowFormula = { str -> actual = str},
            onShowResult = { str -> actual = str},
            onShowToast = { str -> actual = str}
        )
        val presenter = Presenter(view)
        presenter.addNumber('2')

        // when : 숫자를 추가한다.
        presenter.addOperator('+')

        // then : 수식 문자열에 공백과 연산자가 추가된다.
        assertThat(actual).isEqualTo("2 +")
    }

    @Test
    fun `직전 문자가 연산자인 수식 문자열에 숫자를 입력한다`() {
        // given : Presenter를 생성하고 숫자와 연산자를 추가한다.
        var actual = ""
        val view = createView(
            onShowFormula = { str -> actual = str},
            onShowResult = { str -> actual = str},
            onShowToast = { str -> actual = str}
        )
        val presenter = Presenter(view)
        presenter.addNumber('2')
        presenter.addOperator('+')

        // when : 숫자를 추가한다.
        presenter.addNumber('4')

        // then : 수식 문자열에 공백과 숫자가 추가된다.
        assertThat(actual).isEqualTo("2 + 4")
    }

    @Test
    fun `직전 문자가 연산자인 수식 문자열에 연산자를 입력한다`() {
        // given : Presenter를 생성하고 숫자와 연산자를 추가한다.
        var actual = ""
        val view = createView(
            onShowFormula = { str -> actual = str},
            onShowResult = { str -> actual = str},
            onShowToast = { str -> actual = str}
        )
        val presenter = Presenter(view)
        presenter.addNumber('2')
        presenter.addOperator('+')

        // when : 숫자를 추가한다.
        presenter.addOperator('/')

        // then : 직전 문자인 연산자가 새로운 값으로 대체된다.
        assertThat(actual).isEqualTo("2 /")
    }

    @Test
    fun `수식의 마지막 문자를 삭제한다`() {
        // given : Presenter를 생성하고 수식을 추가한다.
        var actual = ""
        val view = createView(
            onShowFormula = { str -> actual = str},
            onShowResult = { str -> actual = str},
            onShowToast = { str -> actual = str}
        )
        val presenter = Presenter(view)
        presenter.addNumber('2')
        presenter.addOperator('+')

        // when : 수식 문자열에의 최근 문자를 삭제 한다.
        presenter.deleteLastStr()

        // then : 직전 문자가 삭제된다.
        assertThat(actual).isEqualTo("2")
    }

    @Test
    fun `수식을 계산한다`() {
        // given : Presenter를 생성하고 수식을 추가한다.
        var actual = ""
        val view = createView(
            onShowFormula = { str -> actual = str},
            onShowResult = { str -> actual = str},
            onShowToast = { str -> actual = str}
        )
        val presenter = Presenter(view)
        presenter.addNumber('2')
        presenter.addOperator('*')
        presenter.addNumber('4')

        // when : 수식을 계산합니다.
        presenter.calculate()

        // then : 수식의 결과값을 보여줍니다.
        assertThat(actual).isEqualTo("8")
    }

    @Test
    fun `완성된 수식이 아닐 경우 계산 요청`() {
        // given : Presenter를 생성하고 불완전한 수식을 추가한다.
        var actual = ""
        val view = createView(
            onShowFormula = { str -> actual = str},
            onShowResult = { str -> actual = str},
            onShowToast = { str -> actual = str}
        )
        val presenter = Presenter(view)
        presenter.addNumber('2')
        presenter.addOperator('*')

        // when : 수식을 계산합니다.
        presenter.calculate()

        // then : 완성되지 않은 수식입니다 노출
        assertThat(actual).isEqualTo("완성되지 않은 수식입니다")
    }

    // region Fixture
    // 가짜 뷰
    private fun createView(onShowFormula: (String) -> Unit,
                           onShowResult: (String) -> Unit,
                           onShowToast: (String) -> Unit): Contract.View = object : Contract.View {
        override fun showFormula(formula: String) {
            onShowFormula(formula)
        }

        override fun showResult(result: String) {
            onShowResult(result)
        }

        override fun showToast(msg: String) {
            onShowToast(msg)
        }

        override lateinit var presenter: Contract.Presenter
    }
}