package camp.nextstep.edu.calculator

import camp.nextstep.edu.domain.CAST_INT_EXCEPTION
import camp.nextstep.edu.domain.Calculator
import camp.nextstep.edu.domain.Expression
import camp.nextstep.edu.domain.Operator

class MainPresenterImpl(
	private val view: MainContract.View
) : MainContract.Presenter {

	private val calculator: Calculator = Calculator()
	private var expression: Expression = Expression()

	override fun plus(operand: String) {
		expression += operand
		view.showExpression(expression)
	}

	override fun plus(operator: Operator) {
		expression += operator
		view.showExpression(expression)
	}

	override fun delete() {
		expression = expression.delete()
		view.showExpression(expression)
	}

	override fun showResultOrThrow() {
		runCatching {
			calculator.calculate(expression.getCompleteExpression())
		}.onSuccess {
			showResult(it)
		}.onFailure {
			showToastIfNeed(it.message)
		}
	}

	private fun showResult(result: Int) {
		view.showResult(result.toString())
		initExpression()
	}

	private fun initExpression() {
		expression = Expression()
	}

	private fun showToastIfNeed(errorMessage: String?) {
		when (errorMessage) {
			Expression.EXP_NOT_COMPLETE -> view.showToast("완성되지 않은 수식입니다.")
			Expression.EXP_IS_BLANK -> view.showToast("계산식이 입력되지 않았습니다.")
			Operator.INVALID_OPERATOR -> view.showToast("유효하지 않은 연산자가 입력되었습니다.")
			CAST_INT_EXCEPTION -> view.showToast("숫자가 아닌 피연산자가 입력되었습니다.")
			Calculator.DIVIDE_BY_ZERO -> view.showToast("0으로 나눌 수 없습니다.")
			else -> Unit
		}
	}
}