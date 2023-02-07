package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.example.domain.Calculator
import com.example.domain.Operand
import com.example.domain.Operator
import com.example.domain.Statement

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val statement: Statement = Statement()
    private val calculator: Calculator = Calculator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            statement.addTerm(Operand(0))
            binding.textView.text = statement.termsToString()
        }

        binding.button1.setOnClickListener {
            statement.addTerm(Operand(1))
            binding.textView.text = statement.termsToString()
        }

        binding.button2.setOnClickListener {
            statement.addTerm(Operand(2))
            binding.textView.text = statement.termsToString()
        }

        binding.button3.setOnClickListener {
            statement.addTerm(Operand(3))
            binding.textView.text = statement.termsToString()
        }

        binding.button4.setOnClickListener {
            statement.addTerm(Operand(4))
            binding.textView.text = statement.termsToString()
        }

        binding.button5.setOnClickListener {
            statement.addTerm(Operand(5))
            binding.textView.text = statement.termsToString()
        }

        binding.button6.setOnClickListener {
            statement.addTerm(Operand(6))
            binding.textView.text = statement.termsToString()
        }

        binding.button7.setOnClickListener {
            statement.addTerm(Operand(7))
            binding.textView.text = statement.termsToString()
        }

        binding.button8.setOnClickListener {
            statement.addTerm(Operand(8))
            binding.textView.text = statement.termsToString()
        }

        binding.button9.setOnClickListener {
            statement.addTerm(Operand(9))
            binding.textView.text = statement.termsToString()
        }

        binding.buttonDivide.setOnClickListener {
            statement.addTerm(Operator.DIVIDE)
            binding.textView.text = statement.termsToString()
        }

        binding.buttonMinus.setOnClickListener {
            statement.addTerm(Operator.SUBTRACT)
            binding.textView.text = statement.termsToString()
        }

        binding.buttonMultiply.setOnClickListener {
            statement.addTerm(Operator.MULTIPLY)
            binding.textView.text = statement.termsToString()
        }

        binding.buttonPlus.setOnClickListener {
            statement.addTerm(Operator.ADD)
            binding.textView.text = statement.termsToString()
        }

        binding.buttonDelete.setOnClickListener {
            statement.removeTerm()
            binding.textView.text = statement.termsToString()
        }

        binding.buttonEquals.setOnClickListener {
            try {
                binding.textView.text = calculator.calculate(statement.termsToString()).toString()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
