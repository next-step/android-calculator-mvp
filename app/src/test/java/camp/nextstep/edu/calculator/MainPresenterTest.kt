package camp.nextstep.edu.calculator

import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var view: MainContract.View

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = MainPresenter(view)
    }

    @Test
    fun `1을 입력하면 1을 보여줌`() {
        presenter.addOperand("1")

        verify {
            view.showExpression("1")
        }
    }

    @Test
    fun `+를 입력하면 빈 값을`() {
        presenter.addOpcode("+")

        verify {
            view.showExpression("")
        }
    }

    @Test
    fun `1 + 3을 입력하면`() {
        presenter.addOperand("1")
        presenter.addOpcode("+")
        presenter.addOperand("3")

        verify {
            view.showExpression("1 + 3")
        }
    }

    @Test
    fun `3 + 2를 계산하면 5가 나온다`() {
        presenter.addOperand("3")
        presenter.addOpcode("+")
        presenter.addOperand("2")

        presenter.calculate()

        verify {
            view.showExpression("5")
        }
    }
}
