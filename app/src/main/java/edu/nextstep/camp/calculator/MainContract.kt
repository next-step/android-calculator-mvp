package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showError(message: String)
        fun showExpression(result: String)
        fun showMemory(histories: List<Memory.MemoryItem>)
        fun hideViewsExceptExpression()
        fun hideViewsExceptMemory()
    }

    interface Presenter {
        fun addToExpression(operator: Operator)
        fun addToExpression(operand: Int)
        fun calculateExpression()
        fun removeLastFromExpression()
        fun updateMemory()
        fun updateExpression()
    }
}