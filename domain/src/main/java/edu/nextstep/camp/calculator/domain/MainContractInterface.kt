package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.Operator

interface MainContractInterface {
    interface View {
        fun showCalculateExpression(expressionStr: String)
        fun showCompletionOfExpressionDataMessage()
    }

    interface Presenter {
        fun inputNumber(number: Int)
        fun inputOperator(operatorData: Operator)
        fun removeLastIndexData()
        fun calculateInputValue()
    }
}