package camp.nextstep.edu.calculator

import com.nextstep.edu.calculator.domain.Calculator
import com.nextstep.edu.calculator.domain.Expression
import com.nextstep.edu.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
) : MainContract.Presenter {

    private var expression: Expression = Expression.EMPTY
    private val calculator: Calculator = Calculator()

    /**
     * 계산결과 실행
     * */
    override fun callEquals() {
        try {
            val result = calculator.evaluate(expression.toString())
            view.showResult(result.toString())
            expression = Expression(listOf(result))
        } catch (e: IllegalArgumentException) {
            view.showErrorMessage(e.message)
        }
    }

    override fun callDelete() {
        expression = expression.deleteOperations()
        view.showExpression(expression)
    }

    override fun addOperator(operator: Operator) {
        expression = expression.addOperator(operator)
        view.showExpression(expression)
    }

    override fun addOperation(operation: Int) {
        expression = expression.addOperand(operation)
        view.showExpression(expression)
    }

}