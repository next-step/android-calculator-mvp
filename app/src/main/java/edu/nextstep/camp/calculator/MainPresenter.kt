package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.EvaluationRecordStore
import edu.nextstep.camp.calculator.domain.ExpressionTokenProcessor
import edu.nextstep.camp.calculator.domain.model.Operand
import edu.nextstep.camp.calculator.domain.model.Operator

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val expressionTokenProcessor = ExpressionTokenProcessor()
    private val evaluationRecordStore = EvaluationRecordStore()
    private var isShowingHistory = false

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
            val record = expressionTokenProcessor.evaluate()
            evaluationRecordStore.record(record)
            view.displayExpression(record.result)
        }
    }

    override fun delete() {
        processToken {
            view.displayExpression(expressionTokenProcessor.delete())
        }
    }

    override fun toggleEvaluationHistory() {
        isShowingHistory = if (isShowingHistory) {
            view.hideEvaluationHistory()
            false
        } else {
            view.showEvaluationHistory(evaluationRecordStore.getEvaluationHistory())
            true
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
