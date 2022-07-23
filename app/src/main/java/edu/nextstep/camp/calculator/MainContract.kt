package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.view.MemoryUIModel


interface MainContract {
    interface View {
        fun setResultTextView(text: String)

        fun showToastIncompleteExpression()

        fun showExpressionMemoryView()

        fun hideExpressionMemoryView()
    }

    interface Presenter {
        fun onClickNumberButton(number: Int)

        fun onClickOperandButton(operator: String)

        fun onClickDeleteButton()

        fun onClickEqualButton()

        fun onClickMemoryButton()

        //fun saveExpressions(expression: Expression, result: String)

        fun getItemList(): List<MemoryUIModel>
    }
}