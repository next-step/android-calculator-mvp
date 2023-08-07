package camp.nextstep.edu.calculator

import camp.nextstep.edu.domain.CAST_INT_EXCEPTION
import camp.nextstep.edu.domain.Calculator
import camp.nextstep.edu.domain.Expression
import camp.nextstep.edu.domain.Operator

class MainPresenterImpl(
	private val view: MainContract.View,
	exp: String = ""
) : MainContract.Presenter {

	private val calculator: Calculator = Calculator()
	private var expression: Expression = Expression(exp)

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
			view.showToast(it.message)
		}
	}

	private fun showResult(result: Int) {
		view.showResult(result.toString())
		initExpression()
	}

	private fun initExpression() {
		expression = Expression()
	}
}