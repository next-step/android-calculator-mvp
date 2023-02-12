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
        try {
            val tempResult = calculator.evaluate(expression.get())
            expression.clear()
            expression.append(Num(tempResult))
            tempResult.toString()
        } catch (e: Exception) {
            onError.invoke()
            expression.toString()
        }
    }
}
