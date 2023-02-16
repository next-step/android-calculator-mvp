package camp.nextstep.edu.calculator

import com.nextstep.edu.domain.CalculationExpression
import com.nextstep.edu.domain.Calculator
import com.nextstep.edu.domain.Operator

class CalculatorPresenter(
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {
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
        var result = ""
        runCatching {
            result = calculate.calculate(calculationExpression.toString()).toString()
            view.showCalculationExpression(result)
        }.onSuccess {
            calculationExpression.resetValueToResult(result.toInt())
        }.onFailure {
            view.alertIncompleteExpression()
        }
    }
}