package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MainPresenterTest {

    private val expression: Expression = Expression.EMPTY
    private lateinit var presenter: MainPresenter

    @BeforeEach
    fun setup() {
        presenter = MainPresenter(expression = expression)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        val actual = presenter
            .expression(number = 1)
            .toString()

        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `빈 수식에 연산자가 입력되면 수식에 추가되지 않습니다`() {
        val presenter = MainPresenter(expression = expression)

        val actual = presenter.expression(operator = Operator.Plus)

        assertThat(actual).isEqualTo(Expression.EMPTY)
    }
}
