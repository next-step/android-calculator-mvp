package camp.nextstep.edu.calculator

import com.example.domain.Calculator
import com.example.domain.Expression
import com.example.domain.Operator

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    private val calculator = Calculator()
    private var expression = Expression()

    override fun removeLastValueInExpression() {
        expression = expression.removeLastValue()
        view.showExpression(expression)
    }

    override fun appendOperandInExpression(value: Int) {
        expression += value
        view.showExpression(expression)
    }

    override fun appendOperatorInExpression(value: Operator) {
        expression += value
        view.showExpression(expression)
    }

    override fun returnResult() {
        return try {
            val result = calculator.evaluate(expression.value()).toString()
            view.showResult(result)
        } catch (exception: IllegalArgumentException) {
            view.showToastMessage(expression)
        }
    }
}