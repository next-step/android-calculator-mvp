package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        binding.buttonPlus.setOnClickListener { displayCalculation(plus) }
        binding.buttonMinus.setOnClickListener { displayCalculation(minus) }
        binding.buttonMultiply.setOnClickListener { displayCalculation(multiply) }
        binding.buttonDivide.setOnClickListener { displayCalculation(divide) }

        binding.buttonEquals.setOnClickListener {
            try {
                val text = binding.textView.text
                binding.textView.text = "$text = ${calculator.evaluate(text.toString())}"
            } catch (e: IllegalStateException) {
                Toast.makeText(this, "완성되지 않은 수식입니다", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonDelete.setOnClickListener {
            val text = binding.textView.text
            if (text.isNotBlank()) {
                binding.textView.text = calculator.clearCalculation(text.toString())
            }
        }
    }

    private fun displayCalculation(newText: String) {
        binding.textView.text = calculator.displayCalculation(binding.textView.text.toString(), newText)
    }
}

