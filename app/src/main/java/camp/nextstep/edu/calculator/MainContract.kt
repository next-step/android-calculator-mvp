/**
 * @author Daewon on 21,August,2023
 *
 */

package camp.nextstep.edu.calculator


interface MainContract {
    interface View {
        var presenter: Presenter
        fun showResult(result: String)
        fun showError(message: String)
    }

    interface Presenter {
        fun clickButton(input: String)

        fun addToInput(input: String): Result<Unit>
        fun removeLast()
        fun calculate()
        fun clearCurrentOperandList()
    }
}
