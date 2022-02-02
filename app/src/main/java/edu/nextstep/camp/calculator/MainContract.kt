package edu.nextstep.camp.calculator

import com.github.dodobest.domain.Result

interface MainContract {
    interface View {

        fun refreshTextView(text: String)
        fun showToastMessage(toastMessage: String)
        fun switchRecyclerViewVisible()
    }
    interface Presenter {

        fun handleInputNum(inputNum: String)
        fun handleInputArithmetic(inputOperation: String)
        fun handleInputDelete()
        fun handleEquals()
        fun getResultList(): ArrayList<Result>
    }

}