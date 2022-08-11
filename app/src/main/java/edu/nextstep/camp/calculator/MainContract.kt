package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.HistoryData
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        var presenter: Presenter

        fun showExpression(text: String)
        fun showErrorToast()
        fun showHistoryView(histories: List<HistoryData>)
        fun hideHistoryView()
    }

    interface Presenter {
        fun appendOperand(operand: Int)
        fun appendOperator(operator: Operator)
        fun deleteLast()
        fun calculate()
        fun toggleHistoryViewMode()
    }
}