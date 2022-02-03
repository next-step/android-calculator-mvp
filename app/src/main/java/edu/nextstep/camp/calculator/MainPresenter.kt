package edu.nextstep.camp.calculator

import com.github.dodobest.domain.InputHandler
import com.github.dodobest.domain.Result
import com.github.dodobest.domain.ResultHandler

class MainPresenter(
    private val view: MainContract.View,
    private val resultHandler: ResultHandler = ResultHandler(),
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
            val expression = inputHandler.getString()
            inputHandler.handleEquals()
            val result = inputHandler.getString()

            view.refreshTextView(result)
            resultHandler.add(expression, "= $result")
            return
        }

        view.showToastMessage(view.getResourceValue(R.string.incomplete_expression))
    }

    override fun getResultList(): ArrayList<Result> {
        return resultHandler.getResultList()
    }
}