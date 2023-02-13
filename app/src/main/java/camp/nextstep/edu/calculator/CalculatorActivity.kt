package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val expression = Expression(Calculator())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()

    }


    private fun setupListener() {

        binding.button0.setOnClickListener {
            expression.updateExpressions(binding.button0.text.toString())
            binding.textView.text = expression.text
        }
        binding.button1.setOnClickListener {
            expression.updateExpressions(binding.button1.text.toString())
            binding.textView.text = expression.text
        }
        binding.button2.setOnClickListener {
            expression.updateExpressions(binding.button2.text.toString())
            binding.textView.text = expression.text
        }
        binding.button3.setOnClickListener {
            expression.updateExpressions(binding.button3.text.toString())
            binding.textView.text = expression.text
        }
        binding.button4.setOnClickListener {
            expression.updateExpressions(binding.button4.text.toString())
            binding.textView.text = expression.text
        }
        binding.button5.setOnClickListener {
            expression.updateExpressions(binding.button5.text.toString())
            binding.textView.text = expression.text
        }
        binding.button6.setOnClickListener {
            expression.updateExpressions(binding.button6.text.toString())
            binding.textView.text = expression.text
        }
        binding.button7.setOnClickListener {
            expression.updateExpressions(binding.button7.text.toString())
            binding.textView.text = expression.text
        }
        binding.button8.setOnClickListener {
            expression.updateExpressions(binding.button8.text.toString())
            binding.textView.text = expression.text
        }

        binding.button9.setOnClickListener {
            expression.updateExpressions(binding.button9.text.toString())
            binding.textView.text = expression.text
        }

        binding.buttonDivide.setOnClickListener {
            expression.updateExpressions(binding.buttonDivide.text.toString())
            binding.textView.text = expression.text
        }
        binding.buttonMinus.setOnClickListener {
            expression.updateExpressions(binding.buttonMinus.text.toString())
            binding.textView.text = expression.text
        }
        binding.buttonMultiply.setOnClickListener {
            expression.updateExpressions(binding.buttonMultiply.text.toString())
            binding.textView.text = expression.text
        }
        binding.buttonPlus.setOnClickListener {
            expression.updateExpressions(binding.buttonPlus.text.toString())
            binding.textView.text = expression.text
        }

        binding.buttonDelete.setOnClickListener {
            expression.delete()
            binding.textView.text = expression.text
        }

        binding.buttonEquals.setOnClickListener {

            try {
                expression.calculateAndReset()
                binding.textView.text = expression.text
            } catch (e: IllegalArgumentException) {
                Toast.makeText(this, "완성되지 않은 수식 입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
