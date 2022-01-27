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

    @DisplayName("숫자를 입력할 수 있다.")
    @ValueSource(strings = ["42", "123", "93562"])
    @ParameterizedTest
    fun inputNumber(number: String) {
        number.map(Char::digitToInt).forEach(presenter::inputNumber)
        verify { view.showExpression(number) }
    }

    @DisplayName("연산자 뒤에 숫자를 입력할 수 있다..")
    @ValueSource(strings = ["42", "123", "93562"])
    @ParameterizedTest
    fun inputNumberAfterOperator(number: String) {
        // given
        presenter.inputNumber(5)
        presenter.inputPlus()
        val expected = "5 + $number"

        // when
        number.map(Char::digitToInt).forEach(presenter::inputNumber)

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("입력된 피연산자가 없을 때, 연산자를 입력해도 변화가 없어야 한다.")
    @Test
    fun inputBlankOperator() {
        // given
        val expected = ""

        // when
        presenter.inputPlus()
        presenter.inputMinus()
        presenter.inputMultiply()
        presenter.inputDivide()

        // then
        verifySequence {
            view.showExpression(expected)
            view.showExpression(expected)
            view.showExpression(expected)
            view.showExpression(expected)
        }
    }

    @DisplayName("덧셈을 입력할 수 있다.")
    @Test
    fun inputPlus() {
        // given
        val number = 7
        val expected = "$number +"
        presenter.inputNumber(number)

        // when
        presenter.inputPlus()

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("뺄셈을 입력할 수 있다.")
    @Test
    fun inputMinus() {
        // given
        val number = 7
        val expected = "$number -"
        presenter.inputNumber(number)

        // when
        presenter.inputMinus()

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("곱셈을 입력할 수 있다.")
    @Test
    fun inputMultiply() {
        // given
        val number = 7
        val expected = "$number *"
        presenter.inputNumber(number)

        // when
        presenter.inputMultiply()

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("나눗셈을 입력할 수 있다.")
    @Test
    fun inputDivide() {
        // given
        val number = 7
        val expected = "$number /"
        presenter.inputNumber(number)

        // when
        presenter.inputDivide()

        // then
        verify { view.showExpression(expected) }
    }

    @DisplayName("수식을 삭제할 수 있다.")
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
        // given
        val expected = ""

        // when
        presenter.deleteLast()
        presenter.deleteLast()

        // then
        verifySequence {
            view.showExpression(expected)
            view.showExpression(expected)
        }
    }

    @DisplayName("수식을 계산할 수 있다.")
    @Test
    fun calculate() {
        // given
        val expression = "2 + 3 * 4 / 2"
        val expected = "10"
        presenter.inputNumber(2)
        presenter.inputPlus()
        presenter.inputNumber(3)
        presenter.inputMultiply()
        presenter.inputNumber(4)
        presenter.inputDivide()
        presenter.inputNumber(2)

        // when
        presenter.calculate()

        // then
        verify {
            view.showExpression(expression)
            view.showExpression(expected)
        }
    }

    @DisplayName("입력된 수식이 완전하지 않을 때, 완성되지 않은 수식임을 화면에 알려아 한다.")
    @Test
    fun calculateIncompleteExpression() {
        // given
        presenter.inputNumber(3)
        presenter.inputPlus()
        val expression = "3 +"

        // when
        presenter.calculate()

        // then
        verify {
            view.showExpression(expression)
            view.showExpressionError()
        }
    }
}
