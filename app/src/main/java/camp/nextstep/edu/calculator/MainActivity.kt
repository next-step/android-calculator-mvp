package camp.nextstep.edu.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculator: Calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        // 번호키
        binding.button0.setOnClickListener { displayCalculation("0") }
        binding.button1.setOnClickListener { displayCalculation("1") }
        binding.button2.setOnClickListener { displayCalculation("2") }
        binding.button3.setOnClickListener { displayCalculation("3") }
        binding.button4.setOnClickListener { displayCalculation("4") }
        binding.button5.setOnClickListener { displayCalculation("5") }
        binding.button6.setOnClickListener { displayCalculation("6") }
        binding.button7.setOnClickListener { displayCalculation("7") }
        binding.button8.setOnClickListener { displayCalculation("8") }
        binding.button9.setOnClickListener { displayCalculation("9") }

        // 사칙연산키
        binding.buttonPlus.setOnClickListener { displayCalculation(plus) }
        binding.buttonMinus.setOnClickListener { displayCalculation(minus) }
        binding.buttonMultiply.setOnClickListener { displayCalculation(multiply) }
        binding.buttonDivide.setOnClickListener { displayCalculation(divide) }

        // 결과키
        binding.buttonEquals.setOnClickListener {
            val text = binding.textView.text
            binding.textView.text = "$text = ${calculator.evaluate(text.toString())}"
        }

        binding.textView.text = "0"
    }

    private fun displayCalculation(newText: String?) {
        if (newText.isNullOrEmpty()) throw IllegalArgumentException()

        val trim = """[^\S\d-+*/]""".toRegex()
        if (trim.matches(newText)) throw IllegalArgumentException()

        val preText = binding.textView.text
        val lastChar = preText.split(" ").last { it.isNotEmpty() }
        val displayText = when {
            preText == "0" && newText.isDigitsOnly() -> newText
            lastChar.isDigitsOnly() && isOperation(newText) -> "$preText $newText "
            isOperation(lastChar) && isOperation(newText) -> "${preText.replaceRange(preText.length - 3, preText.length, " $newText ")}"
            else -> "$preText$newText"
        }

        binding.textView.text = displayText
    }

    private fun isOperation(toCheck: String) = """[-+*/]""".toRegex().matches(toCheck)
}

