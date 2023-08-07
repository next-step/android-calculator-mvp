package camp.nextstep.edu.calculator

import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private var expression = Expression()
    override fun addOperand(operand: String) {
        view.showExpression(expression.addOperand(operand))
    }

    override fun addOpcode(opcode: String) {
        var formula = ""
        runCatching {
            formula = expression.addOpcode(opcode)
        }.getOrElse {
            view.showError(it.message)
        }
        view.showExpression(formula)
    }

    override fun deleteFormula() {
        view.showExpression(expression.removeLast())
    }

    override fun calculate() {
        runCatching {
            val result = Calculator.evaluate(expression)
            expression = Expression(result)
            view.showExpression(result)
        }.getOrElse {
            view.showError(it.message)
        }
    }
}
