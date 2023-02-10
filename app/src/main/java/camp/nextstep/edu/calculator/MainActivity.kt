package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.calculator.Calculator
import camp.nextstep.edu.domain.calculator.isNumeric

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val expressions = mutableListOf<String>()

    private val calculator = Calculator()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { appendExpression("0") }
        binding.button1.setOnClickListener { appendExpression("1") }
        binding.button2.setOnClickListener { appendExpression("2") }
        binding.button3.setOnClickListener { appendExpression("3") }
        binding.button4.setOnClickListener { appendExpression("4") }
        binding.button5.setOnClickListener { appendExpression("5") }
        binding.button6.setOnClickListener { appendExpression("6") }
        binding.button7.setOnClickListener { appendExpression("7") }
        binding.button8.setOnClickListener { appendExpression("8") }
        binding.button9.setOnClickListener { appendExpression("9") }

        binding.buttonPlus.setOnClickListener { appendExpression("+") }
        binding.buttonMinus.setOnClickListener { appendExpression("-") }
        binding.buttonMultiply.setOnClickListener { appendExpression("*") }
        binding.buttonDivide.setOnClickListener { appendExpression("/") }
        binding.buttonDelete.setOnClickListener { removeLastExpression() }
        binding.buttonEquals.setOnClickListener {
            binding.textView.text = calculate(calculator) { showErrorToast() }
        }
    }

    private fun appendExpression(item: String) {
        expressions.lastOrNull()?.let { last ->
            when {
                last.isNumeric() && item.isNumeric() -> {
                    expressions.removeLast()
                    expressions.add(last + item)
                }
                !last.isNumeric() && !item.isNumeric() -> {
                    expressions.removeLast()
                    expressions.add(item)
                }
                else -> {
                    expressions.add(item)
                }
            }
        } ?: run {
            if (item.isNumeric()) {
                expressions.add(item)
            }
        }

        binding.textView.text = expressions.joinToString(separator = " ")
    }

    private fun removeLastExpression() {
        if (expressions.isEmpty()) return

        val last = expressions.last()
        when {
            !last.isNumeric() -> expressions.removeLast()
            else -> {
                val trimmed = last.split("").drop(1).dropLast(1)
                expressions.removeLast()
                expressions.addAll(trimmed.dropLast(1))
            }
        }

        binding.textView.text = expressions.joinToString(separator = " ")
    }

    private fun calculate(
        calculator: Calculator,
        onError: () -> Unit
    ): String {
        val expressionString = expressions.joinToString(separator = " ")
        return try {
            val result = calculator.evaluate(expressionString).toString()
            expressions.clear()
            expressions.add(result)
            result
        } catch (e: Exception) {
            onError()
            expressionString
        }
    }

    private fun showErrorToast() {
        Toast.makeText(
            applicationContext,
            "완성되지 않은 수식입니다",
            Toast.LENGTH_SHORT
        ).show()
    }
}
