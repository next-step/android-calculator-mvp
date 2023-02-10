package camp.nextstep.edu.calculator

import com.nextstep.edu.calculator.domain.Calculator
import com.nextstep.edu.calculator.domain.Expression
import com.nextstep.edu.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    private var expression : Expression = Expression.EMPTY
    private val calculator: Calculator = Calculator()

    override fun callEquals() {
        runCatching {
            calculator.evaluate(expression.toString())
        }.onSuccess { result ->
            view.showResult(result.toString())
        }.onFailure {
            when (it) {
                is IllegalArgumentException -> view.showExceptionToast(it.message)
                else -> throw it
            }
        }
    }

    override fun callDelete() {
        expression = expression.deleteOperations()
        view.showExpression(expression)
    }

    override fun addExpression(operations: Any) {
        expression = when (operations) {
            is Operator -> expression.addOperator(operations)
            is Int -> expression.addOperand(operations)
            else -> throw IllegalArgumentException("Invalid Type")
        }
        view.showExpression(expression)
    }

}