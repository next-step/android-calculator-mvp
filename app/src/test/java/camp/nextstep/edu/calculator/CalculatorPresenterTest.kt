package camp.nextstep.edu.calculator

import com.google.common.truth.Truth.assertThat
import com.nextstep.calculator.Operator.* // ktlint-disable no-wildcard-imports
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertAll

/**
 * @author 박소연
 * @created 2023/02/25
 * @updated 2023/03/01
 * @desc 계산기 Presenter의 단위테스트
 */
class CalculatorPresenterTest {
    private lateinit var presenter: CalculatorPresenter
    private lateinit var view: CalculatorContract.View

    @Before
    fun setUp() {
        view = mockk()
        presenter = CalculatorPresenter(view)
    }

    @Test
    fun 숫자가_입력되면_수식에_추가되고_변경된_수식을_보여줘야_한다() {
        // given
        val inputSlot = slot<String>()
        every { view.showExpression(capture(inputSlot)) } answers { nothing }

        // when
        presenter.addInput(1)

        // then
        val actual = inputSlot.captured

        assertAll(
            { assertThat(actual).isEqualTo("1") }
        )
        verify { view.showExpression(actual) }
    }

    @Test
    fun 연산자가_입력되면_수식에_추가되고_변경된_수식을_보여줘야_한다() {
        // given
        val inputSlot = slot<String>()
        every { view.showExpression(capture(inputSlot)) } answers { nothing }
        presenter.addInput(1)

        // when
        presenter.addInput(PLUS)

        // then
        val actual = inputSlot.captured
        assertAll(
            { assertThat(actual).isEqualTo("1 +") }
        )
        verify { view.showExpression(actual) }
    }

    @Test
    fun 문자를_지우면_수식의_마지막을_지우고_변경된_수식을_보여줘야_한다() {
        // given
        val inputSlot = slot<String>()
        every { view.showExpression(capture(inputSlot)) } answers { nothing }
        presenter.addInput(1)
        presenter.addInput(PLUS)
        presenter.addInput(2)

        // when
        presenter.removeLastInput()

        // then
        val actual = inputSlot.captured
        assertAll(
            { assertThat(actual).isEqualTo("1 +") }
        )
        verify { view.showExpression(actual) }
    }

    @Test
    fun 계산을_시작하면_입력된_수식으로_계산하여_결과를_보여줘야_한다() {
        // given
        val inputSlot = slot<String>()
        every { view.showExpression(capture(inputSlot)) } answers { nothing }

        presenter.addInput(1)
        presenter.addInput(PLUS)
        presenter.addInput(2)

        // when
        presenter.calculate()

        // then
        val actual = inputSlot.captured
        assertAll(
            { assertThat(actual).isEqualTo("3") }
        )
        verify { view.showExpression(actual) }
    }
}
