package camp.nextstep.edu.calculator

interface MainContract {
    interface View {
        var presenter: MainPresenter

        fun showResult(expressions: String)
        fun showErrorToastMessage()
    }

    interface Presenter {
        fun onButtonClicked(text: String)
        fun onDeleteClicked()
        fun onEqualsClicked()
        fun onMemoryClicked()
    }
}