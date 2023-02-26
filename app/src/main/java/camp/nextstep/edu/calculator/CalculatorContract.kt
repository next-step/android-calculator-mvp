package camp.nextstep.edu.calculator

/**
 * @author 박소연
 * @created 2023/02/25
 * @updated 2023/02/25
 * @desc 계산기 Contract
 */

interface CalculatorContract {
    interface View {
        var presenter: Presenter

        // Presenter에서 호출
        fun showExpression(input: String)
    }

    // ViewController에서 호출
    interface Presenter {
        fun addInput(input: String)
        fun removeLastInput()
        fun calculate()
    }
}
