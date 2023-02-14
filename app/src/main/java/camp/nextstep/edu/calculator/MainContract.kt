package camp.nextstep.edu.calculator

import camp.nextstep.edu.domain.calculator.ExpressionItem


interface MainContract {

    interface View {

        fun showExpression(expression: String)

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
