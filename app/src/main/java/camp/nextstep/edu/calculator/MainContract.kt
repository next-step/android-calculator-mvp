package camp.nextstep.edu.calculator

import camp.nextstep.edu.domain.calculator.Calculator
import camp.nextstep.edu.domain.calculator.Expression
import camp.nextstep.edu.domain.calculator.ExpressionItem


interface MainContract {

    interface View {
        var presenter: Presenter


        fun showExpression(expression: String)

        fun showResult(result: String)

        fun showError()
    }

    interface Presenter {

        fun appendExpression(expressionItem: ExpressionItem)

        fun removeLastExpression()

        fun calculate(
            onError: () -> Unit
        )
    }
}
