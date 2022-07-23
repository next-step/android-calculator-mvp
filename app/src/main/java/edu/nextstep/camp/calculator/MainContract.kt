package edu.nextstep.camp.calculator


interface MainContract {
    interface View {
        fun setResultTextView(text: String)

        fun showToastIncompleteExpression()
    }

    interface Presenter {
        fun onClickNumberButton(number: Int)

        fun onClickOperandButton(operator: String)

        fun onClickDeleteButton()

        fun onClickEqualButton()
    }
}