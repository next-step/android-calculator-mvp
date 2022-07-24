package edu.nextstep.camp.calculator.domain

class MainPresenter(private val view: MainContractInterface.View): MainContractInterface.Presenter {

    private val mCalculator = Calculator()
    private var mExpressionStr = Expression.EMPTY

    override fun inputNumber(number: Int) {
        mExpressionStr += number

        if (isValidString(mExpressionStr.toString())) {
            view.showCalculateExpression(mExpressionStr.toString())
        } else {
            // mExpressionStr.toString()이 유효하지 않으면
            // 아무것도 실행하지 않기 위해 else문을 비움.
        }
    }

    override fun inputOperator(operatorData: Operator) {
        /*expression += operator
        view.showExpression(expression.toString())*/
        mExpressionStr += operatorData

        if (isValidString(mExpressionStr.toString())) {
            view.showCalculateExpression(mExpressionStr.toString())
        } else {
            // mExpressionStr.toString()이 유효하지 않으면
            // 아무것도 실행하지 않기 위해 else문을 비움.
        }
    }

    override fun removeLastIndexData() {
        mExpressionStr = mExpressionStr.removeLast()

        if (isValidString(mExpressionStr.toString())) {
            view.showCalculateExpression(mExpressionStr.toString())
        } else {
            // mExpressionStr.toString()이 유효하지 않으면
            // 아무것도 실행하지 않기 위해 else문을 비움.
        }
    }

    override fun calculateInputValue() {

        if (isValidString(mExpressionStr.toString())) {
            val calculateResult = mCalculator.calculate(mExpressionStr.toString())
            if (calculateResult == null) {
                view.showCompletionOfExpressionDataMessage()
                return
            } else {
                // calculateResult가 null이면
                // 아래 로직을 실행시키기 위해 else문을 비움.
            }

            mExpressionStr = Expression.EMPTY + calculateResult
            view.showCalculateExpression(mExpressionStr.toString())
        } else {
            // mExpressionStr.toString()이 유효하지 않으면
            // 아무것도 실행하지 않기 위해 else문을 비움.
        }

    }

    fun isValidString(strData: String?): Boolean {
        var isValid: Boolean = false;
        if (strData != null && !(strData.equals(""))) {
            isValid = true;
        } else {
            // strData가 유효하지 않으면 isValid가
            // false로 초기화되어 있기에 else문을 비움.
        }

        return isValid;
    }

}