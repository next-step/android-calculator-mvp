package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.example.domain.Calculator
import com.example.domain.Expression
import com.example.domain.Operator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var expression = Expression()
        set(value) {
            field = value
            binding.textView.text = expression.value()
        }

    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button0.setOnClickListener { appendOperandInExpression(0) }
        binding.button1.setOnClickListener { appendOperandInExpression(1) }
        binding.button2.setOnClickListener { appendOperandInExpression(2) }
        binding.button3.setOnClickListener { appendOperandInExpression(3) }
        binding.button4.setOnClickListener { appendOperandInExpression(4) }
        binding.button5.setOnClickListener { appendOperandInExpression(5) }
        binding.button6.setOnClickListener { appendOperandInExpression(6) }
        binding.button7.setOnClickListener { appendOperandInExpression(7) }
        binding.button8.setOnClickListener { appendOperandInExpression(8) }
        binding.button9.setOnClickListener { appendOperandInExpression(9) }

        binding.buttonPlus.setOnClickListener { appendOperatorInExpression(Operator.PLUS) }
        binding.buttonMinus.setOnClickListener { appendOperatorInExpression(Operator.MINUS) }
        binding.buttonMultiply.setOnClickListener { appendOperatorInExpression(Operator.MULTI) }
        binding.buttonDivide.setOnClickListener { appendOperatorInExpression(Operator.DIVIDE) }
        binding.buttonDelete.setOnClickListener { removeLastValueInExpression() }
        binding.buttonEquals.setOnClickListener { binding.textView.text = returnResult() }
    }

    private fun returnResult(): String {
        return try {
            calculator.evaluate(expression.value()).toString()
        } catch (exception: IllegalArgumentException) {
            showToastMessage()
            expression.value()
        }
    }

    private fun showToastMessage() {
        Toast.makeText(
            applicationContext,
            "The '${expression.value()}' is not a valid formula.",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun removeLastValueInExpression() {
        expression = expression.removeLastValue()
    }

    private fun appendOperandInExpression(value: Int) {
        expression += value
    }

    private fun appendOperatorInExpression(value: Operator) {
        expression += value
    }
}
