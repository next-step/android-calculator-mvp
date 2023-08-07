package camp.nextstep.edu.calculator

import camp.nextstep.edu.domain.Expression
import camp.nextstep.edu.domain.Operator

interface MainContract {
	interface View {
		var presenter: Presenter

		fun showExpression(expression: Expression)
		fun showResult(result: String)
		fun showToast(message: String?)
	}

	interface Presenter {
		fun plus(operand: String)
		fun plus(operator: Operator)
		fun delete()
		fun showResultOrThrow()
	}
}