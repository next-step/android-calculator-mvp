package camp.nextstep.edu.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.CalculationOperator
import camp.nextstep.edu.domain.Evaluator
import camp.nextstep.edu.domain.ExpressionHandler

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val expressionHandler = ExpressionHandler()
    private val evaluator = Evaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            button0.setOnClickListener { showExpression("0") }
            button1.setOnClickListener { showExpression("1") }
            button2.setOnClickListener { showExpression("2") }
            button3.setOnClickListener { showExpression("3") }
            button4.setOnClickListener { showExpression("4") }
            button5.setOnClickListener { showExpression("5") }
            button6.setOnClickListener { showExpression("6") }
            button7.setOnClickListener { showExpression("7") }
            button8.setOnClickListener { showExpression("8") }
            button9.setOnClickListener { showExpression("9") }

            buttonPlus.setOnClickListener { showExpression(" ${CalculationOperator.PLUS.symbol} ") }
            buttonMinus.setOnClickListener { showExpression(" ${CalculationOperator.MINUS.symbol} ") }
            buttonDivide.setOnClickListener { showExpression(" ${CalculationOperator.DIVIDE.symbol} ") }
            buttonMultiply.setOnClickListener { showExpression(" ${CalculationOperator.MULTIPLY.symbol} ") }

            buttonDelete.setOnClickListener {
                expressionHandler.deleteLast()
            }

            buttonEquals.setOnClickListener {
                val result = evaluator.evaluate(expressionHandler.expression)
                binding.textView.text = result.toString()
            }
        }
    }

    private fun showExpression(inputValue: String) {
        expressionHandler.addInputValue(inputValue)
        binding.textView.text = expressionHandler.expression
    }
}
