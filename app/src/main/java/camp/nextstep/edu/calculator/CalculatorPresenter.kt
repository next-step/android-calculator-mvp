package camp.nextstep.edu.calculator

import com.nextstep.calculator.Calculator
import com.nextstep.calculator.Expression

/**
 * @author 박소연
 * @created 2023/02/25
 * @updated 2023/02/25
 * @desc 계산기 비즈니스로직을 수행하는 Presenter
 */
class CalculatorPresenter(
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {
    private val calculator = Calculator()
    private val expression by lazy { Expression() }

    override fun addInput(input: String) {
        expression.checkInput(input)
        view.showExpression(expression.getInputExpression())
    }

    override fun removeLastInput() {
        expression.removeInput()
        view.showExpression(expression.getInputExpression())
    }

    override fun calculate() {
        val expression = expression.getInputExpression()
        val result = calculator.calculate(expression)

        view.showExpression(result.toString())
    }
}
