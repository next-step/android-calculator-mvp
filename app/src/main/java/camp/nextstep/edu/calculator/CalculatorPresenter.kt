package camp.nextstep.edu.calculator

import com.nextstep.edu.domain.CalculationExpression
import com.nextstep.edu.domain.Calculator
import com.nextstep.edu.domain.Operator

class CalculatorPresenter(
    private val view: CalculatorViewInterface
) : CalculatorPresenterInterface {
    private val calculationExpression: CalculationExpression = CalculationExpression()
    private val calculate: Calculator = Calculator()

    override fun appendExpression(operand: Int) {
        calculationExpression.add(operand)
        view.showCalculationExpression(calculationExpression.toString())
    }

    override fun appendExpression(operator: Operator) {
        calculationExpression.add(operator)
        view.showCalculationExpression(calculationExpression.toString())
    }

    override fun removeExpression() {
        calculationExpression.remove()
        view.showCalculationExpression(calculationExpression.toString())
    }

    override fun calculate() {
        lateinit var result: String
        runCatching {
            result = calculate.calculate(calculationExpression.toString()).toString()
            view.showCalculationResult(result)
        }.onSuccess {
            calculationExpression.clear(result.toInt())
        }.onFailure {
            view.alertIncompleteExpression()
        }
    }

    override fun clear(value: Int) {
        calculationExpression.clear(value)
    }
}