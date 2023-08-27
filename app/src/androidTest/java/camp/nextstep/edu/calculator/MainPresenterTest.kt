package camp.nextstep.edu.calculator

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View

    @Before
    fun setUp() {
        view = mockk<MainActivity>()
        presenter = MainPresenter(view)
    }

    @Test
    fun `피연산자가_입력되면_피연산자가_추가되고_화면에_노출된다`() {
        every { view.showExpression(any()) } just Runs

        val expected = "32"
        presenter.addInputValue("3")
        presenter.addInputValue("2")

        verify {
            view.showExpression(expected)
        }
    }

    @Test
    fun `연산자가_피연산자_뒤에_입력되면_연산자가_추가되고_화면에_노출된다`() {
        every { view.showExpression(any()) } just Runs

        val expected = "32 + "
        presenter.addInputValue("3")
        presenter.addInputValue("2")
        presenter.addInputValue(" + ")

        verify {
            view.showExpression(expected)
        }
    }

    @Test
    fun `지우기_버튼을_클릭하면_수식의_마지막이_지워지고_화면에_노출된다`() {
        every { view.showExpression(any()) } just Runs

        val expected = "32"
        presenter.addInputValue("3")
        presenter.addInputValue("2")
        presenter.addInputValue(" + ")
        presenter.deleteLast()

        verify {
            view.showExpression(expected)
        }
    }

    @Test
    fun `계산_버튼을_클릭하면_계산되고_화면에_노출된다`() {
        every { view.showExpression(any()) } just Runs

        val expected = "35"
        presenter.addInputValue("3")
        presenter.addInputValue("2")
        presenter.addInputValue(" + ")
        presenter.addInputValue("3")
        presenter.evaluate()

        verify {
            view.showExpression(expected)
        }
    }
}