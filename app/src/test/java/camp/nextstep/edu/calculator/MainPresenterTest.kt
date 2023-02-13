package camp.nextstep.edu.calculator

import com.google.common.truth.Truth
import com.nextstep.edu.calculator.domain.Expression
import com.nextstep.edu.calculator.domain.Operator
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private val view: MainContract.View = mockk<MainContract.View>(relaxed = true)

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }
        presenter = MainPresenter(view)

        // when 1 입력
        presenter.addOperation(1)

        // then 1이 출력되어야 한다
        val actual = expressionSlot.captured
        Truth.assertThat(actual.toString()).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `숫자가 입력 후 결과보기를 호출 시 결과가 나와야한다`() {
        // given
        val expressionSlot = slot<String>()
        every { view.showResult(capture(expressionSlot)) } just runs
        val expression = Expression(listOf(1, Operator.Plus, 3))
        presenter = MainPresenter(view, expression)

        // when 1 + 3 계산결과 실행
        presenter.callEquals()

        // then 4가 출력되어야 한다
        val actual = expressionSlot.captured
        Truth.assertThat(actual.toString()).isEqualTo("4")
        verify { view.showResult(actual) }
    }

    @Test
    fun `숫자 입력 후 지우기를 호출 시 하나 지워져야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        val expression = Expression(listOf(123))
        presenter = MainPresenter(view, expression)

        // when 123입력 후 Delete 실행
        presenter.callDelete()


        // then 12가 출력되어야 한다
        val actual = expressionSlot.captured
        Truth.assertThat(actual.toString()).isEqualTo("12")
        verify { view.showExpression(actual) }
    }
}