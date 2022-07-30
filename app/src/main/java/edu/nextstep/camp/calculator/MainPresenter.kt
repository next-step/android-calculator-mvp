package edu.nextstep.camp.calculator

import edu.nextstep.camp.common.UiText
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class MainPresenter(
	private val view: MainContract.View,
	private val calculator: Calculator
): MainContract.Presenter {

	private var expression = Expression.EMPTY

	override fun addNumberToExpression(number: Int) {
		expression += number

		showCurrentExpression()
	}

	override fun addOperatorToExpression(operator: Operator) {
		expression += operator

		showCurrentExpression()
	}

	override fun removeLastToken() {
		expression = expression.removeLast()

		showCurrentExpression()
	}

	override fun calculateCurrentExpression() {
		val result = calculator.calculate(expression.toString())
		if (result == null) {
			view.showErrorMessage(UiText.StringResource(R.string.incomplete_expression))
			return
		}

		expression = Expression.EMPTY + result

		view.showResult(result)
	}

	private fun showCurrentExpression() {
		view.showExpression(expression)
	}
}