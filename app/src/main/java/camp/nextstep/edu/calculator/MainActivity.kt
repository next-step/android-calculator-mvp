package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.example.domain.Calculator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculator = Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button0.setOnClickListener {
            clickButton("0")
        }

        binding.button1.setOnClickListener {
            clickButton("1")
        }

        binding.button2.setOnClickListener {
            clickButton("2")
        }

        binding.button3.setOnClickListener {
            clickButton("3")
        }

        binding.button4.setOnClickListener {
            clickButton("4")
        }

        binding.button5.setOnClickListener {
            clickButton("5")
        }

        binding.button6.setOnClickListener {
            clickButton("6")
        }

        binding.button7.setOnClickListener {
            clickButton("7")
        }

        binding.button8.setOnClickListener {
            clickButton("8")
        }

        binding.button9.setOnClickListener {
            clickButton("9")
        }

        binding.buttonPlus.setOnClickListener {
            clickButton("+")
        }

        binding.buttonMinus.setOnClickListener {
            clickButton("-")
        }

        binding.buttonMultiply.setOnClickListener {
            clickButton("*")
        }

        binding.buttonDivide.setOnClickListener {
            clickButton("/")
        }

        binding.buttonDelete.setOnClickListener {
            calculator.removeLastInput()
            binding.textView.text = getOperand()
        }

        binding.buttonEquals.setOnClickListener {
            runCatching { calculator.calculate(getOperand()) }.onSuccess { result ->
                binding.textView.text = result.toString()
            }.onFailure { throwable ->
                Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clickButton(input: String) {
        if (calculator.addInput(input)) {
            binding.textView.text = getOperand()
        }
    }

    private fun getOperand() = calculator.currentOperandList.joinToString(" ")
}
