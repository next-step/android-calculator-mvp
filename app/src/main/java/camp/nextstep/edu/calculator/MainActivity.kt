package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.example.domain.*

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val statement: Statement = Statement()
    private val calculator: Calculator = Calculator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            addTerm(Operand(0))
        }

        binding.button1.setOnClickListener {
            addTerm(Operand(1))
        }

        binding.button2.setOnClickListener {
            addTerm(Operand(2))
        }

        binding.button3.setOnClickListener {
            addTerm(Operand(3))
        }

        binding.button4.setOnClickListener {
            addTerm(Operand(4))
        }

        binding.button5.setOnClickListener {
            addTerm(Operand(5))
        }

        binding.button6.setOnClickListener {
            addTerm(Operand(6))
        }

        binding.button7.setOnClickListener {
            addTerm(Operand(7))
        }

        binding.button8.setOnClickListener {
            addTerm(Operand(8))
        }

        binding.button9.setOnClickListener {
            addTerm(Operand(9))
        }

        binding.buttonDivide.setOnClickListener {
            addTerm(Operator.DIVIDE)
        }

        binding.buttonMinus.setOnClickListener {
            addTerm(Operator.SUBTRACT)
        }

        binding.buttonMultiply.setOnClickListener {
            addTerm(Operator.MULTIPLY)
        }

        binding.buttonPlus.setOnClickListener {
            addTerm(Operator.ADD)
        }

        binding.buttonDelete.setOnClickListener {
            removeTerm()
        }

        binding.buttonEquals.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        try {
            binding.textView.text = calculator.calculate(statement.termsToString()).toString()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun addTerm(term: OperationTerm) {
        statement.addTerm(term)
        textRebuild()
    }

    private fun removeTerm() {
        statement.removeTerm()
        textRebuild()
    }

    private fun textRebuild() {
        binding.textView.text = statement.termsToString()
    }
}
