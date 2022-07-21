package edu.nextstep.camp.calculator

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    @MockK private lateinit var view: MainContract.View
    @InjectMockKs private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `0부터 9까지 각 버튼을 누르면 화면에 해당 숫자가 표시된다`() {
        (0..9).forEach { number ->
            // given
            presenter = MainPresenter(view)

            // when
            presenter.enterNumber(number)

            // then
            verify { view.showExpression("$number") }
        }
    }
}
