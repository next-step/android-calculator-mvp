package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.RecordData

interface MainContract {
    interface View {
        fun showCalculateExpression(expressionStr: String)
        fun showCompletionOfExpressionDataMessage()
        fun hideRecord()
        fun showRecord()
        fun loadRecordList(recordList: List<RecordData>)
    }

    interface Presenter {
        fun inputNumber(number: Int)
        fun inputOperator(operatorData: Operator)
        fun removeLastIndexData()
        fun calculateInputValue()
        fun clickRecord(isShown: Boolean)
        fun loadRecordData()
    }
}