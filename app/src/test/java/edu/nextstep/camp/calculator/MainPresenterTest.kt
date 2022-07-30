package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.common.UiText
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

@Suppress("NonAsciiCharacters")
class MainPresenterTest {
	private lateinit var presenter: MainPresenter
	private lateinit var view: MainContract.View

	@Before
	fun setUp() {
		view = mockk()
		presenter = MainPresenter(
			view = view,
			calculator = Calculator()
		)
	}

	@Test
	fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }

		// when
		presenter.addNumberToExpression(1)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자가 입력된 상태에서 다른 숫자를 입력하면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)

		// when
		presenter.addNumberToExpression(2)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("12")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자가 입력된 상태에서 + 연산자를 입력하면 수식에 추가되고 숫자 + 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)

		// when
		presenter.addOperatorToExpression(Operator.Plus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 +")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자가 입력된 상태에서 - 연산자를 입력하면 수식에 추가되고 숫자 - 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)

		// when
		presenter.addOperatorToExpression(Operator.Minus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 -")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자가 입력된 상태에서 × 연산자를 입력하면 수식에 추가되고 숫자 × 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)

		// when
		presenter.addOperatorToExpression(Operator.Multiply)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ×")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자가 입력된 상태에서 ÷ 연산자를 입력하면 수식에 추가되고 숫자 ÷ 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)

		// when
		presenter.addOperatorToExpression(Operator.Divide)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ÷")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `아무런 입력이 없는 상태에서 + 연산자를 입력하면 수식에 추가되지 않고 빈 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }

		// when
		presenter.addOperatorToExpression(Operator.Plus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `아무런 입력이 없는 상태에서 - 연산자를 입력하면 수식에 추가되지 않고 빈 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }

		// when
		presenter.addOperatorToExpression(Operator.Minus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `아무런 입력이 없는 상태에서 × 연산자를 입력하면 수식에 추가되지 않고 빈 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }

		// when
		presenter.addOperatorToExpression(Operator.Multiply)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `아무런 입력이 없는 상태에서 ÷ 연산자를 입력하면 수식에 추가되지 않고 빈 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }

		// when
		presenter.addOperatorToExpression(Operator.Divide)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `아무런 입력이 없는 상태에서 지우기를 하면 수식에 변화가 없고 빈 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }

		// when
		presenter.removeLastToken()

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("")
		verify { view.showExpression(actual) }
	}


	@Test
	fun `숫자, + 연산자가 입력된 상태에서 + 연산자를 입력하는 경우 연산자가 변경되고 숫자 + 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Plus)

		// when
		presenter.addOperatorToExpression(Operator.Plus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 +")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, + 연산자가 입력된 상태에서 - 연산자를 입력하는 경우 연산자가 변경되고 숫자 - 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Plus)

		// when
		presenter.addOperatorToExpression(Operator.Minus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 -")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, + 연산자가 입력된 상태에서 × 연산자를 입력하는 경우 연산자가 변경되고 숫자 × 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Plus)

		// when
		presenter.addOperatorToExpression(Operator.Multiply)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ×")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, + 연산자가 입력된 상태에서 ÷ 연산자를 입력하는 경우 연산자가 변경되고 숫자 ÷ 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Plus)

		// when
		presenter.addOperatorToExpression(Operator.Divide)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ÷")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, - 연산자가 입력된 상태에서 + 연산자를 입력하는 경우 연산자가 변경되고 변경된 숫자 + 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Minus)

		// when
		presenter.addOperatorToExpression(Operator.Plus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 +")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, - 연산자가 입력된 상태에서 - 연산자를 입력하는 경우 연산자가 변경되고 숫자 - 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Minus)

		// when
		presenter.addOperatorToExpression(Operator.Minus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 -")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, - 연산자가 입력된 상태에서 × 연산자를 입력하는 경우 연산자가 변경되고 숫자 × 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Minus)

		// when
		presenter.addOperatorToExpression(Operator.Multiply)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ×")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, - 연산자가 입력된 상태에서 ÷ 연산자를 입력하는 경우 연산자가 변경되고 숫자 ÷ 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Minus)

		// when
		presenter.addOperatorToExpression(Operator.Divide)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ÷")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, × 연산자가 입력된 상태에서 + 연산자를 입력하는 경우 연산자가 변경되고 숫자 + 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Multiply)

		// when
		presenter.addOperatorToExpression(Operator.Plus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 +")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, × 연산자가 입력된 상태에서 - 연산자를 입력하는 경우 연산자가 변경되고 숫자 - 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Multiply)

		// when
		presenter.addOperatorToExpression(Operator.Minus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 -")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, × 연산자가 입력된 상태에서 × 연산자를 입력하는 경우 연산자가 변경되고 숫자 × 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Multiply)

		// when
		presenter.addOperatorToExpression(Operator.Multiply)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ×")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, × 연산자가 입력된 상태에서 ÷ 연산자를 입력하는 경우 연산자가 변경되고 숫자 ÷ 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Multiply)

		// when
		presenter.addOperatorToExpression(Operator.Divide)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ÷")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, ÷ 연산자가 입력된 상태에서 + 연산자를 입력하는 경우 연산자가 변경되고 숫자 + 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Divide)

		// when
		presenter.addOperatorToExpression(Operator.Plus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 +")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, ÷ 연산자가 입력된 상태에서 - 연산자를 입력하는 경우 연산자가 변경되고 숫자 - 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Divide)

		// when
		presenter.addOperatorToExpression(Operator.Minus)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 -")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, ÷ 연산자가 입력된 상태에서 × 연산자를 입력하는 경우 연산자가 변경되고 숫자 × 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Divide)

		// when
		presenter.addOperatorToExpression(Operator.Multiply)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ×")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, ÷ 연산자가 입력된 상태에서 ÷ 연산자를 입력하는 경우 연산자가 변경되고 숫자 ÷ 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Divide)

		// when
		presenter.addOperatorToExpression(Operator.Divide)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ÷")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, + 연산자가 입력된 상태에서 숫자를 입력하는 경우 연산식에 숫자가 추가되고 숫자 + 숫자 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Plus)

		// when
		presenter.addNumberToExpression(1)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 + 1")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, - 연산자가 입력된 상태에서 숫자를 입력하는 경우 연산식에 숫자가 추가되고 숫자 - 숫자 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Minus)

		// when
		presenter.addNumberToExpression(1)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 - 1")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, × 연산자가 입력된 상태에서 숫자를 입력하는 경우 연산식에 숫자가 추가되고 숫자 × 숫자 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Multiply)

		// when
		presenter.addNumberToExpression(1)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 × 1")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `숫자, ÷ 연산자가 입력된 상태에서 숫자를 입력하는 경우 연산식에 숫자가 추가되고 숫자 ÷ 숫자 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(1)
		presenter.addOperatorToExpression(Operator.Divide)

		// when
		presenter.addNumberToExpression(1)

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("1 ÷ 1")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `32 + 1 수식이 입력되었을때 지우기를 수행하면 1이 제거되고 32 + 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(3)
		presenter.addNumberToExpression(2)
		presenter.addOperatorToExpression(Operator.Plus)
		presenter.addNumberToExpression(1)

		// when
		presenter.removeLastToken()

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("32 +")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `32 + 수식이 입력되었을때 지우기를 수행하면 +가 제거되고 32 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(3)
		presenter.addNumberToExpression(2)
		presenter.addOperatorToExpression(Operator.Plus)

		// when
		presenter.removeLastToken()

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("32")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `32 수식이 입력되었을때 지우기를 수행하면 2가 제거되고 3 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(3)
		presenter.addNumberToExpression(2)

		// when
		presenter.removeLastToken()

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("3")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `3 수식이 입력되었을때 지우기를 수행하면 3이 제거되고 빈 수식을 보여줘야 한다`() {
		// given
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }
		presenter.addNumberToExpression(3)

		// when
		presenter.removeLastToken()

		// then
		val actual = expressionSlot.captured
		assertThat(actual.toString()).isEqualTo("")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `32 + 1 수식이 입력되었을때 연산을 수행하면 계산 결과인 33을 화면에 보여줘야 한다`() {
		// given
		val resultSlot = slot<Int>()
		every { view.showResult(capture(resultSlot)) } answers { nothing }
		every { view.showExpression(any()) } answers { nothing }

		presenter.addNumberToExpression(3)
		presenter.addNumberToExpression(2)
		presenter.addOperatorToExpression(Operator.Plus)
		presenter.addNumberToExpression(1)

		// when
		presenter.calculateCurrentExpression()

		// then
		val actual = resultSlot.captured
		assertThat(actual.toString()).isEqualTo("33")
		verify { view.showResult(actual) }
	}

	@Test
	fun `32 + 수식이 입력되었을때 연산을 수행하면 유효하지 않은 수식에 대한 에러 메시지를 보여줘야 한다`() {
		// given
		val errorMessageSlot = slot<UiText>()
		every { view.showErrorMessage(capture(errorMessageSlot)) } answers { nothing }
		every { view.showExpression(any()) } answers { nothing }

		presenter.addNumberToExpression(3)
		presenter.addNumberToExpression(2)
		presenter.addOperatorToExpression(Operator.Plus)

		// when
		presenter.calculateCurrentExpression()

		// then
		val actual = errorMessageSlot.captured
		assertThat(actual).isEqualTo(UiText.StringResource(R.string.incomplete_expression))
		verify { view.showErrorMessage(actual) }
	}
}