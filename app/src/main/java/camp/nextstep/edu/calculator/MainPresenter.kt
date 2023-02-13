package camp.nextstep.edu.calculator

import camp.nextstep.edu.domain.calculator.Calculator
import camp.nextstep.edu.domain.calculator.Expression
import camp.nextstep.edu.domain.calculator.ExpressionItem
import camp.nextstep.edu.domain.calculator.Num


class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    private val calculator = Calculator()

    private val expression = Expression { displayExpression ->
        view.showExpression(displayExpression)
    }


    override fun appendExpression(expressionItem: ExpressionItem) {
        expression.append(expressionItem)
    }

    override fun removeLastExpression() {
        expression.removeLastExpression()
    }

    override fun calculate(
        onError: () -> Unit
    ) {
        runCatching {
            calculator.evaluate(expression.expressions)
        }.onSuccess { result ->
            expression.clear()
            expression.append(Num(result))
        }.onFailure {
            onError.invoke()
        }
    }
}
