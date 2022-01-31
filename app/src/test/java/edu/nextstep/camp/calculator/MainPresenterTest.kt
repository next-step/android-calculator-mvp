package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @BeforeEach
    internal fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }

    @Test
    @DisplayName("Presenter 테스트 예시")
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    @DisplayName("입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다.")
    fun addToExpression1() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    @DisplayName("입력된 피연산자가 있을 때, 기존 숫자 뒤에 해당 숫자가 화면에 보여야 한다. 예를 들면, 8이 입력되어 있을 때 9를 입력하면 89가 보여야 한다.")
    fun addToExpression2() {
        // given
        val expressionSlot = slot<String>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter.addToExpression(8)

        // when
        presenter.addToExpression(9)

        // then
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("89")
        verify { view.showExpression(actual) }
    }
}