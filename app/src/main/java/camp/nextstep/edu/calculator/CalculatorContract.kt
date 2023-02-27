package camp.nextstep.edu.calculator

import com.nextstep.calculator.Operator

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
        fun addInput(input: Int)
        fun addInput(input: Operator)
        fun removeLastInput()
        fun calculate()
    }
}
