package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.ExpressionTokenProcessor
import edu.nextstep.camp.calculator.domain.model.Operand
import edu.nextstep.camp.calculator.domain.model.Operator

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val expressionTokenProcessor = ExpressionTokenProcessor()

    override fun addOperatorToken(operator: Operator) {
        processToken {
            view.displayExpression(expressionTokenProcessor.processOperatorInput(operator))
        }
    }

    override fun addOperandToken(operand: Operand) {
        processToken {
            view.displayExpression(expressionTokenProcessor.processNumberInput(operand))
        }
    }

    override fun evaluate() {
        processToken {
            view.displayExpression(expressionTokenProcessor.evaluate())
        }
    }

    override fun delete() {
        processToken {
            view.displayExpression(expressionTokenProcessor.delete())
        }
    }

    private fun processToken(processBlock : () -> Unit) {
        kotlin.runCatching {
            processBlock.invoke()
        }.onFailure {
            view.handleExceptions(it)
        }
    }
}
