package camp.nextstep.edu.calculator

import com.google.common.truth.Truth.assertThat
import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ExpressionPresenterTest {
    private lateinit var presenter: ExpressionPresenter
    private lateinit var view: Contract.View

    @Before
    fun setUp() {
        view = mockk()
        presenter = ExpressionPresenter(view)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.display(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addExpressionText("1")

        // then
        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.display(actual) }
    }

    @Test
    fun `1 입력하면 1이출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        presenter.addExpressionText("1")

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.display(actual) }
    }

    @Test
    fun `5 + 1 수식을 입력하면 5 + 1 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf("5", "+", "1"))

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("5 + 1")
        verify { view.display(actual) }
    }

    @Test
    fun `89를 입력하면_89가 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf("8", "9"))

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("89")
        verify { view.display(actual) }
    }

    @Test
    fun `+ 입력하면 빈문자가 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf("+"))

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("")
        verify { view.display(actual) }
    }

    @Test
    fun `1 + 입력하면 1 + 가출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf("1", "+"))

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("1 + ")
        verify { view.display(actual) }
    }

    @Test
    fun `1 + 입력하고 - 를 입력하면 1 - 가 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf("1", "+"))
        `수식을 입력한다`(listOf("-"))

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("1 - ")
        verify { view.display(actual) }
    }

    @Test
    fun `삭제를 호출하면 빈문자가 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        presenter.removeExpressionItem()

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("")
        verify { view.display(actual) }
    }

    @Test
    fun `32 + 1 입력하고 삭제를 호출하면 32 + 가출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf("3", "2", "+", "1"))
        presenter.removeExpressionItem()

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("32 + ")
        verify { view.display(actual) }
    }

    @Test
    fun `32 + 입력하고 삭제를 호출하면 32 가 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf("3", "2", "+"))
        presenter.removeExpressionItem()

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("32")
        verify { view.display(actual) }
    }

    @Test
    fun `32 입력하고 삭제를 호출하면 3 출력된다`() {
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf("3", "2"))
        presenter.removeExpressionItem()

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("3")
        verify { view.display(actual) }
    }

    @Test
    fun `3 + 2 입력하고 계산을 호출하면 5 가 출력된다`() {
        // 수식을_입력한다("3 + 2")
        val expressionSlot = `뷰호출을 정의한다`()

        `수식을 입력한다`(listOf("3", "+", "2"))
        presenter.calculate()

        val actual: String = expressionSlot.captured
        assertThat(actual).isEqualTo("5")
        verify { view.display(actual) }
    }

    private fun `뷰호출을 정의한다`(): CapturingSlot<String> {
        val expressionSlot = slot<String>()
        every { view.display(capture(expressionSlot)) } answers { nothing }
        return expressionSlot
    }

    private fun `수식을 입력한다`(expressions: List<String>) {
        for (expression in expressions) {
            presenter.addExpressionText(expression)
        }
    }
}
