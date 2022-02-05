package edu.nextstep.camp.calculator

import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private val view: MainContract.View = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        presenter = MainPresenter(view)
    }

    @ValueSource(strings = ["42", "123", "93562"])
    @ParameterizedTest(name = "{0}를 입력하면 View에 {0}를 그리도록 요청해야 한다.")
    fun inputNumber(number: String) {
        number.map(Char::digitToInt).forEach(presenter::inputNumber)
        verify { view.showExpression(number) }
    }

    @ValueSource(strings = ["42", "123", "93562"])
    @ParameterizedTest(name = "5 + {0}을 입력하면 5 + {0}을 그리도록 요청해야 한다.")
    fun inputNumberAfterOperator(number: String) {
        // given
        presenter.inputNumber(5)
        presenter.inputPlus()

        // when
        val expected = "5 + $number"
        number.map(Char::digitToInt).forEach(presenter::inputNumber)

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("입력된 피연산자가 없을 때, 연산자를 입력해도 변화가 없어야 한다.")
    @Test
    fun inputBlankOperator() {
        // when
        val expected = ""
        presenter.inputPlus()
        presenter.inputMinus()
        presenter.inputMultiply()
        presenter.inputDivide()

        // then
        verify(exactly = 4) { view.showExpression(expected) }
    }

    @DisplayName("덧셈을 입력하면 View 에 +를 그리도록 요청해야 한다.")
    @Test
    fun inputPlus() {
        // given
        val number = 7
        presenter.inputNumber(number)

        // when
        val expected = "$number +"
        presenter.inputPlus()

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("뺄셈을 입력하면 View 에 - 그리도록 요청해야 한다.")
    @Test
    fun inputMinus() {
        // given
        val number = 7
        presenter.inputNumber(number)

        // when
        val expected = "$number -"
        presenter.inputMinus()

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("곱셈을 입력하면 View 에 *를 그리도록 요청해야 한다.")
    @Test
    fun inputMultiply() {
        // given
        val number = 7
        presenter.inputNumber(number)

        // when
        val expected = "$number *"
        presenter.inputMultiply()

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("나눗셈을 입력하면 View 에 /를 그리도록 요청해야 한다.")
    @Test
    fun inputDivide() {
        // given
        val number = 7
        presenter.inputNumber(number)

        // when
        val expected = "$number /"
        presenter.inputDivide()

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("32 + 1 수식을 삭제하면 마지막 값이 삭제된 텍스트를 View 에 그리도록 요청해야 한다.")
    @Test
    fun deleteLast() {
        // given
        presenter.inputNumber(3)
        presenter.inputNumber(2)
        presenter.inputPlus()
        presenter.inputNumber(1)

        // when
        presenter.deleteLast()
        presenter.deleteLast()
        presenter.deleteLast()
        presenter.deleteLast()
        presenter.deleteLast()

        // then
        verifySequence {
            view.showExpression("3")
            view.showExpression("32")
            view.showExpression("32 +")
            view.showExpression("32 + 1")
            view.showExpression("32 +")
            view.showExpression("32")
            view.showExpression("3")
            view.showExpression("")
            view.showExpression("")
        }
    }

    @Test
    fun deleteNothing() {
        // when
        val expected = ""
        presenter.deleteLast()
        presenter.deleteLast()

        // then
        verifySequence {
            view.showExpression(expected)
            view.showExpression(expected)
        }
    }

    @DisplayName("2 + 3 * 4 / 2 를 계산하면 View 에 10을 그리도록 요청해야 한다.")
    @Test
    fun calculate() {
        // given
        presenter.inputNumber(2)
        presenter.inputPlus()
        presenter.inputNumber(3)
        presenter.inputMultiply()
        presenter.inputNumber(4)
        presenter.inputDivide()
        presenter.inputNumber(2)

        // when
        val expected = "10"
        presenter.calculate()

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("입력된 수식이 완전하지 않을 때, 완성되지 않은 수식임을 화면에 알려아 한다.")
    @Test
    fun calculateIncompleteExpression() {
        // given
        presenter.inputNumber(3)
        presenter.inputPlus()

        // when
        presenter.calculate()

        // then
        verify { view.showExpressionError() }
    }

    @DisplayName("3 + 5 =를 누르고 결과 8이 나온 다음 모두 지운 뒤 10 - 3 = 을 눌러 결과 7이 나온 상태에서 시계 버튼을 누르면 계산 기록이 보여한다.")
    @Test
    fun showHistories() {
        // given
        presenter.inputNumber(3)
        presenter.inputPlus()
        presenter.inputNumber(5)
        presenter.calculate()

        presenter.deleteLast()

        presenter.inputNumber(10)
        presenter.inputMinus()
        presenter.inputNumber(3)
        presenter.calculate()

        // when
        val expected = listOf(
            HistoryModel("3 + 5", "= 8"),
            HistoryModel("10 - 3", "= 7")
        )

        // then
        verify { view.notifyHistories(expected) }
    }
}
