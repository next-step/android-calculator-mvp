package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.ExpressionStorage

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val expressionStorage = ExpressionStorage()

        setNumberButtonListener(expressionStorage)
        setOperatorButtonListener(expressionStorage)

        binding.buttonDelete.setOnClickListener { binding.textView.text = expressionStorage.removeLast() }
        binding.buttonEquals.setOnClickListener {
            runCatching { binding.textView.text = Calculator.evaluate(expressionStorage.getFormularString()) }
            .getOrElse {
                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setOperatorButtonListener(expressionStorage: ExpressionStorage) {
        binding.buttonPlus.setOnClickListener { binding.textView.text = expressionStorage.add(" + ") }
        binding.buttonMinus.setOnClickListener { binding.textView.text = expressionStorage.add(" - ") }
        binding.buttonMultiply.setOnClickListener { binding.textView.text = expressionStorage.add(" * ") }
        binding.buttonDivide.setOnClickListener { binding.textView.text = expressionStorage.add(" / ") }
    }

    private fun setNumberButtonListener(expressionStorage: ExpressionStorage) {
        binding.button0.setOnClickListener { binding.textView.text = expressionStorage.add("0") }
        binding.button1.setOnClickListener { binding.textView.text = expressionStorage.add("1") }
        binding.button2.setOnClickListener { binding.textView.text = expressionStorage.add("2") }
        binding.button3.setOnClickListener { binding.textView.text = expressionStorage.add("3") }
        binding.button4.setOnClickListener { binding.textView.text = expressionStorage.add("4") }
        binding.button5.setOnClickListener { binding.textView.text = expressionStorage.add("5") }
        binding.button6.setOnClickListener { binding.textView.text = expressionStorage.add("6") }
        binding.button7.setOnClickListener { binding.textView.text = expressionStorage.add("7") }
        binding.button8.setOnClickListener { binding.textView.text = expressionStorage.add("8") }
        binding.button9.setOnClickListener { binding.textView.text = expressionStorage.add("9") }
    }
}
