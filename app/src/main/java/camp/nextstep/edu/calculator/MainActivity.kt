package camp.nextstep.edu.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.Calculator
import camp.nextstep.edu.domain.ExpressionHandler

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val expressionHandler = ExpressionHandler()
    private val calculator = Calculator()

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

            buttonPlus.setOnClickListener { showExpression(" + ") }
            buttonMinus.setOnClickListener { showExpression(" - ") }
            buttonDivide.setOnClickListener { showExpression(" ÷ ") }
            buttonMultiply.setOnClickListener { showExpression(" × ") }

            buttonDelete.setOnClickListener {
                expressionHandler.deleteLast()
            }

            buttonEquals.setOnClickListener {
                val result = calculator.evaluate(expressionHandler.getExpression())
                binding.textView.text = result.toString()
            }
        }
    }

    private fun showExpression(inputValue: String) {
        expressionHandler.addInputValue(inputValue)
        binding.textView.text = expressionHandler.getExpression()
    }
}
