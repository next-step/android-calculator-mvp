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
    private val calculator = Calculator()
    private var expression = Expression()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { appendOperand(0) }
        binding.button1.setOnClickListener { appendOperand(1) }
        binding.button2.setOnClickListener { appendOperand(2) }
        binding.button3.setOnClickListener { appendOperand(3) }
        binding.button4.setOnClickListener { appendOperand(4) }
        binding.button5.setOnClickListener { appendOperand(5) }
        binding.button6.setOnClickListener { appendOperand(6) }
        binding.button7.setOnClickListener { appendOperand(7) }
        binding.button8.setOnClickListener { appendOperand(8) }
        binding.button9.setOnClickListener { appendOperand(9) }

        binding.buttonPlus.setOnClickListener { appendOperator(Operator.PLUS) }
        binding.buttonMinus.setOnClickListener { appendOperator(Operator.MINUS) }
        binding.buttonMultiply.setOnClickListener { appendOperator(Operator.MULTIPLY) }
        binding.buttonDivide.setOnClickListener { appendOperator(Operator.DIV) }

        binding.buttonDelete.setOnClickListener { removeLastValue() }
        binding.buttonEquals.setOnClickListener { calculateExpression() }
    }

    private fun appendOperand(operand: Int) {
        expression = expression.appendOperand(operand)
        bindExpressionInTextView()
    }

    private fun appendOperator(op: Operator) {
        expression = expression.appendOperator(op)
        bindExpressionInTextView()
    }

    private fun removeLastValue() {
        expression = expression.removeLastValue()
        bindExpressionInTextView()
    }

    private fun calculateExpression() {
        try {
            binding.textView.text =
                calculator.evaluate(expression.getExpressions()).toString()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun bindExpressionInTextView() {
        binding.textView.text = expression.getExpressions()
    }
}
