package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.RecordData

interface MainContract {
    interface View {
        fun showCalculateExpression(expressionStr: String)
        fun showCompletionOfExpressionDataMessage()
        fun loadRecordList(recordList: List<RecordData>)
        fun showRecord()
        fun hideRecord()
    }

    interface Presenter {
        fun inputNumber(number: Int)
        fun inputOperator(operatorData: Operator)
        fun removeLastIndexData()
        fun calculateInputValue()
        fun loadCalculatorRecord()
        fun clickCalculatorRecord(isShowRecord: Boolean)
    }
}