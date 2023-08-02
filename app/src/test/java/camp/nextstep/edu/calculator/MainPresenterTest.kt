package camp.nextstep.edu.calculator

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @Before
    fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }

    @Test
    fun `1을 클릭하면 1이 보여야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }

        // when: 1을 누르면
        presenter.onButtonClicked("1")

        // then: 1이 보여야 한다
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("1")
        verify { view.showResult(actual) }
    }

    @Test
    fun `12 을 클릭하면 12 이 보여야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }

        // when: 12를 누르면
        presenter.onButtonClicked("1")
        presenter.onButtonClicked("2")

        // then: 12가 보여야 한다
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("12")
        verify { view.showResult(actual) }
    }

    @Test
    fun `12 + 을 클릭하면 12 + 이 보여야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }

        // when: 12 + 를 누르면
        presenter.onButtonClicked("1")
        presenter.onButtonClicked("2")
        presenter.onButtonClicked("+")

        // then: 12 + 가 보여야 한다
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("12 + ")
        verify { view.showResult(actual) }
    }

    @Test
    fun `12 + 클릭 후 삭제을 클릭하면 12이 보여야 한다`() {
        // given: 12 + 를 클릭한다
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }
        presenter.onButtonClicked("1")
        presenter.onButtonClicked("2")
        presenter.onButtonClicked("+")

        // when: 삭제 버튼을 누르면
        presenter.onDeleteClicked()


        // then: 12가 보여야 한다
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("12")
        verify { view.showResult(actual) }
    }

    @Test
    fun `12 + 클릭 후 12을 클릭하면 12 + 12이 보여야 한다`() {
        // given: 12 + 12를 클릭한다
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }
        presenter.onButtonClicked("1")
        presenter.onButtonClicked("2")
        presenter.onButtonClicked("+")

        // when: 12 를 클릭한다
        presenter.onButtonClicked("1")
        presenter.onButtonClicked("2")


        // then: 12 + 12가 보여야 한다
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("12 + 12")
        verify { view.showResult(actual) }
    }

    @Test
    fun `12 + 12 클릭 후 = 을 클릭하면 24이 보여야 한다`() {
        // given: 12 + 12를 클릭한다
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }
        presenter.onButtonClicked("1")
        presenter.onButtonClicked("2")
        presenter.onButtonClicked("+")
        presenter.onButtonClicked("1")
        presenter.onButtonClicked("2")

        // when: = 를 클릭한다
        presenter.onEqualsClicked()

        // then: 24가 보여야 한다
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("24")
        verify { view.showResult(actual) }
    }

    @Test
    fun `12 + 클릭 후 = 을 클릭하면 에러 토스트 메시지가 보여야 한다`() {
        // given: 12 + 를 클릭한다
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }
        every { view.showErrorToastMessage() } answers { nothing }
        presenter.onButtonClicked("1")
        presenter.onButtonClicked("2")
        presenter.onButtonClicked("+")

        // when: = 를 클릭한다
        presenter.onEqualsClicked()

        // then: 에러 토스트 메시지가 보여야 한다
        verify { view.showErrorToastMessage() }
    }

    @Test
    fun `초기 상태에서 + 클릭 후 = 을 클릭하면 아무 반응이 없어야 한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } answers { nothing }

        // when: + 를 클릭한다
        presenter.onButtonClicked("+")

        // then: 에러 토스트 메시지가 보여야 한다
        val actual = expressionSlot.captured
        assertThat(actual).isEqualTo("")
        verify { view.showResult(actual) }
    }
}