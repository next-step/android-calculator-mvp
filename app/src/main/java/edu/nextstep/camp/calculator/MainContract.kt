package edu.nextstep.camp.calculator

<<<<<<< HEAD
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.RecordData
=======
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
>>>>>>> 545fb3c (feat: 피드백 반영)

interface MainContract {
    interface View {
        fun showCalculateExpression(expressionStr: String)
        fun showCompletionOfExpressionDataMessage()
<<<<<<< HEAD
        fun hideRecord()
        fun showRecord()
        fun loadRecordList(recordList: List<RecordData>)
=======
>>>>>>> 545fb3c (feat: 피드백 반영)
    }

    interface Presenter {
        fun inputNumber(number: Int)
        fun inputOperator(operatorData: Operator)
        fun removeLastIndexData()
        fun calculateInputValue()
<<<<<<< HEAD
        fun clickRecord(isShown: Boolean)
        fun loadRecordData()
=======
>>>>>>> 545fb3c (feat: 피드백 반영)
    }
}