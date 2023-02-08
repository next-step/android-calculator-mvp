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
    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { binding.textView.text = appendOperandInExpression(0) }
        binding.button1.setOnClickListener { binding.textView.text = appendOperandInExpression(1) }
        binding.button2.setOnClickListener { binding.textView.text = appendOperandInExpression(2) }
        binding.button3.setOnClickListener { binding.textView.text = appendOperandInExpression(3) }
        binding.button4.setOnClickListener { binding.textView.text = appendOperandInExpression(4) }
        binding.button5.setOnClickListener { binding.textView.text = appendOperandInExpression(5) }
        binding.button6.setOnClickListener { binding.textView.text = appendOperandInExpression(6) }
        binding.button7.setOnClickListener { binding.textView.text = appendOperandInExpression(7) }
        binding.button8.setOnClickListener { binding.textView.text = appendOperandInExpression(8) }
        binding.button9.setOnClickListener { binding.textView.text = appendOperandInExpression(9) }

        binding.buttonPlus.setOnClickListener {
            binding.textView.text = appendOperatorInExpression(Operator.PLUS)
        }
        binding.buttonMinus.setOnClickListener {
            binding.textView.text = appendOperatorInExpression(Operator.MINUS)
        }
        binding.buttonMultiply.setOnClickListener {
            binding.textView.text = appendOperatorInExpression(Operator.MULTI)
        }
        binding.buttonDivide.setOnClickListener {
            binding.textView.text = appendOperatorInExpression(Operator.DIVIDE)
        }
        binding.buttonDelete.setOnClickListener { binding.textView.text = removeWordInExpression() }
        binding.buttonEquals.setOnClickListener { binding.textView.text = returnResult() }
    }

    private fun returnResult(): String {
        return try {
            calculator.evaluate(expression.value()).toString()
        }catch (exception: IllegalArgumentException) {
            showToastMessage()
            expression.value()
        }
    }

    private fun showToastMessage() {
        Toast.makeText(applicationContext, "완성되지 않은 수식입니다. \n${expression.value()}", Toast.LENGTH_SHORT).show()
    }

    private fun removeWordInExpression(): String {
        expression = expression.dropLastInExpression()
        return expression.value()
    }

    private fun appendOperandInExpression(value: Int): String {
        expression += value
        return expression.value()
    }

    private fun appendOperatorInExpression(value: Operator): String {
        expression += value
        return expression.value()
    }
}
