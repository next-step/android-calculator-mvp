package camp.nextstep.edu.calculator

import com.example.domain.Calculator
import com.example.domain.Expression
import com.example.domain.Operator

class CalculatePresenter(
    private val view: CalculateContract.View,
    private var expression: Expression = Expression()
) : CalculateContract.Presenter {

    private val calculator = Calculator()

    override fun appendOperand(operand: Int) {
        expression = expression.appendOperand(operand)
    }

    override fun appendOperator(op: Operator) {
        expression = expression.appendOperator(op)
    }

    override fun calculateExpression() {
        try {
            val result = calculator.evaluate(expression.getExpressions()).toString()
            view.showExpression(result)
        } catch (e: Exception) {
            view.showErrorMessage(e.message.toString())
        }
    }

    override fun removeLastValue() {
        expression = expression.removeLastValue()
    }
}