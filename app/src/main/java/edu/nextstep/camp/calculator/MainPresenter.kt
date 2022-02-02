package edu.nextstep.camp.calculator

import android.content.res.Resources
import com.github.dodobest.domain.InputHandler

class MainPresenter(
    private val view: MainContract.View,
    private val inputHandler: InputHandler = InputHandler()
) : MainContract.Presenter {

    override fun handleInputNum(inputNum: String) {
        inputHandler.handleInputNum(inputNum)
        view.refreshTextView(inputHandler.getString())
    }

    override fun handleInputArithmetic(inputOperation: String) {
        inputHandler.handleInputArithmetic(inputOperation)
        view.refreshTextView(inputHandler.getString())
    }

    override fun handleInputDelete() {
        inputHandler.handleInputDelete()
        view.refreshTextView(inputHandler.getString())
    }

    override fun handleEquals() {
        if (inputHandler.checkExpressionCanCalculated()) {
            inputHandler.handleEquals()
            view.refreshTextView(inputHandler.getString())
            return
        }

        view.showToastMessage(Resources.getSystem().getString(R.string.incomplete_expression))
    }
}