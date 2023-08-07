package camp.nextstep.edu.calculator

import camp.nextstep.edu.domain.Expression
import camp.nextstep.edu.domain.Operator
import com.google.common.truth.Truth.assertThat
import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MainPresenterImplTest {
	private lateinit var presenter: MainContract.Presenter
	private lateinit var view: MainContract.View

	@Before
	fun setUp() {
		view = mockk(relaxed = true)
		presenter = MainPresenterImpl(view)
	}

	@Test
	fun `피연산자가 추가되면, 수식에 추가되고 변경된 수식을 보여준다`() {
		// given
		val slotExpression = createSlotExpression()

		// when
		presenter.plus("1")

		// then
		val actual = slotExpression.captured
		assertThat(actual.toString()).isEqualTo("1")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `연산자가 추가되면, 수식에 추가되고 변경된 수식을 보여준다`() {
		// given
		val slotExpression = createSlotExpression()
		presenter = MainPresenterImpl(view, "1")

		// when
		presenter.plus(Operator.PLUS)

		// then
		val actual = slotExpression.captured
		assertThat(actual.toString()).isEqualTo("1 + ")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `피연산자가 삭제되면, 수식에서 제거되고 변경된 수식을 보여준다`() {
		// given
		val slotExpression = createSlotExpression()
		presenter = MainPresenterImpl(view, "1")

		// when
		presenter.delete()

		// then
		val actual = slotExpression.captured
		assertThat(actual.toString()).isEqualTo("")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `연산자가 삭제되면, 수식에서 제거되고 변경된 수식을 보여준다`() {
		// given
		val slotExpression = createSlotExpression()
		presenter = MainPresenterImpl(view, "1+")

		// when
		presenter.delete()

		// then
		val actual = slotExpression.captured
		assertThat(actual.toString()).isEqualTo("1")
		verify { view.showExpression(actual) }
	}

	@Test
	fun `수식이 유효할때, 계산이 되면, 결과값을 보여준다`() {
		// given
		val result = createSlotResult()
		presenter = MainPresenterImpl(view, "1+1")

		// when
		presenter.showResultOrThrow()

		// then
		val actual = result.captured
		assertThat(actual).isEqualTo("2")
		verify { view.showResult(actual) }
	}

	@Test
	fun `수식이 유효하지 않을때, 계산이 되면, 토스트 메세지가 노출된다`() {
		// given
		presenter = MainPresenterImpl(view, "1+")

		// when
		presenter.showResultOrThrow()

		// then
		verify { view.showToast(Expression.EXP_NOT_COMPLETE) }
	}

	private fun createSlotExpression(): CapturingSlot<Expression> {
		val expressionSlot = slot<Expression>()
		every { view.showExpression(capture(expressionSlot)) } answers { nothing }

		return expressionSlot
	}

	private fun createSlotResult(): CapturingSlot<String> {
		val result = slot<String>()
		every { view.showResult(capture(result)) } answers { nothing }

		return result
	}
}