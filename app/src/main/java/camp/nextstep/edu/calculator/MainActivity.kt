package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.calculator.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val calculator = Calculator()

    private val expression = Expression { displayExpression ->
        binding.textView.text = displayExpression
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { appendExpression(Num(0)) }
        binding.button1.setOnClickListener { appendExpression(Num(1)) }
        binding.button2.setOnClickListener { appendExpression(Num(2)) }
        binding.button3.setOnClickListener { appendExpression(Num(3)) }
        binding.button4.setOnClickListener { appendExpression(Num(4)) }
        binding.button5.setOnClickListener { appendExpression(Num(5)) }
        binding.button6.setOnClickListener { appendExpression(Num(6)) }
        binding.button7.setOnClickListener { appendExpression(Num(7)) }
        binding.button8.setOnClickListener { appendExpression(Num(8)) }
        binding.button9.setOnClickListener { appendExpression(Num(9)) }

        binding.buttonPlus.setOnClickListener { appendExpression(Operators.of("+")) }
        binding.buttonMinus.setOnClickListener { appendExpression(Operators.of("-")) }
        binding.buttonMultiply.setOnClickListener { appendExpression(Operators.of("*")) }
        binding.buttonDivide.setOnClickListener { appendExpression(Operators.of("/")) }
        binding.buttonDelete.setOnClickListener { removeLastExpression() }
        binding.buttonEquals.setOnClickListener {
            binding.textView.text = calculate(calculator, expression) { showErrorToast() }
        }
    }

    private fun appendExpression(expressionItem: ExpressionItem) {
        expression.append(expressionItem)
    }

    private fun removeLastExpression() {
        expression.removeLastExpression()
    }

    private fun calculate(
        calculator: Calculator,
        expression: Expression,
        onError: () -> Unit
    ) =
        try {
            val result = calculator.evaluate(expression.get()).toString()
            result
        } catch (e: Exception) {
            onError.invoke()
            expression.toString()
        }

    private fun showErrorToast() {
        Toast.makeText(
            applicationContext,
            "완성되지 않은 수식입니다",
            Toast.LENGTH_SHORT
        ).show()
    }
}
