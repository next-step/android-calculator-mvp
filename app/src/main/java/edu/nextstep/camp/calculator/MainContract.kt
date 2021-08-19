package edu.nextstep.camp.calculator

import com.joseph.domain.CalculateRecord
import com.joseph.domain.Expression
import com.joseph.domain.Operator

interface MainContract {
    interface View {
        fun displayExpression(expression: Expression)
        fun showIncompleteExpressionToast()
        fun showCalculateResults()
        fun refreshRecyclerView(records: List<CalculateRecord>)
    }

    interface Presenter {
        fun addExpression(number: Int)
        fun addExpression(operator: Operator)
        fun removeAtLastExpression()
        fun calculate()
        fun showCalculateResults()
        fun addCalculateRecord(record: CalculateRecord)
    }
}