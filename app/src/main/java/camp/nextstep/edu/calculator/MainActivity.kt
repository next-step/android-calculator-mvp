package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var expression = Expression()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { binding.textView.text = expression.addOperand("0") }
        binding.button1.setOnClickListener { binding.textView.text = expression.addOperand("1") }
        binding.button2.setOnClickListener { binding.textView.text = expression.addOperand("2") }
        binding.button3.setOnClickListener { binding.textView.text = expression.addOperand("3") }
        binding.button4.setOnClickListener { binding.textView.text = expression.addOperand("4") }
        binding.button5.setOnClickListener { binding.textView.text = expression.addOperand("5") }
        binding.button6.setOnClickListener { binding.textView.text = expression.addOperand("6") }
        binding.button7.setOnClickListener { binding.textView.text = expression.addOperand("7") }
        binding.button8.setOnClickListener { binding.textView.text = expression.addOperand("8") }
        binding.button9.setOnClickListener { binding.textView.text = expression.addOperand("9") }

        binding.buttonPlus.setOnClickListener { binding.textView.text = expression.addOpCode("+") }
        binding.buttonMinus.setOnClickListener { binding.textView.text = expression.addOpCode("-") }
        binding.buttonMultiply.setOnClickListener { binding.textView.text = expression.addOpCode("*") }
        binding.buttonDivide.setOnClickListener { binding.textView.text = expression.addOpCode("/") }

        binding.buttonDelete.setOnClickListener { binding.textView.text = expression.removeLast() }
        binding.buttonEquals.setOnClickListener { calculateExpression() }
    }

    private fun calculateExpression() {
        runCatching {
            val result = Calculator.evaluate(expression.getFormulaString())
            expression = Expression(result)
            binding.textView.text = result
        }.getOrElse {
            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
        }
    }
}
