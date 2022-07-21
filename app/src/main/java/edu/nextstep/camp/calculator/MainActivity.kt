package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.*
import edu.nextstep.camp.calculator.domain.expression.Expressions
import edu.nextstep.camp.calculator.domain.expression.Operand

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculator = Calculator()
    private var expression = Expressions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            expression += 0
            binding.textView.text = expression.toString()
        }
        binding.button1.setOnClickListener {
            expression += 1
            binding.textView.text = expression.toString()
        }
        binding.button2.setOnClickListener {
            expression += 2
            binding.textView.text = expression.toString()
        }
        binding.button3.setOnClickListener {
            expression += 3
            binding.textView.text = expression.toString()
        }
        binding.button4.setOnClickListener {
            expression += 4
            binding.textView.text = expression.toString()
        }
        binding.button5.setOnClickListener {
            expression += 5
            binding.textView.text = expression.toString()
        }
        binding.button6.setOnClickListener {
            expression += 6
            binding.textView.text = expression.toString()
        }
        binding.button7.setOnClickListener {
            expression += 7
            binding.textView.text = expression.toString()
        }
        binding.button8.setOnClickListener {
            expression += 8
            binding.textView.text = expression.toString()
        }
        binding.button9.setOnClickListener {
            expression += 9
            binding.textView.text = expression.toString()
        }
        binding.buttonPlus.setOnClickListener {
            expression += edu.nextstep.camp.calculator.domain.expression.Operator.Plus
            binding.textView.text = expression.toString()
        }
        binding.buttonMinus.setOnClickListener {
            expression += edu.nextstep.camp.calculator.domain.expression.Operator.Minus
            binding.textView.text = expression.toString()
        }
        binding.buttonMultiply.setOnClickListener {
            expression += edu.nextstep.camp.calculator.domain.expression.Operator.Mult
            binding.textView.text = expression.toString()
        }
        binding.buttonDivide.setOnClickListener {
            expression += edu.nextstep.camp.calculator.domain.expression.Operator.Div
            binding.textView.text = expression.toString()
        }
        binding.buttonDelete.setOnClickListener {
            expression = expression.removeLast()
            binding.textView.text = expression.toString()
        }
        binding.buttonEquals.setOnClickListener {
            kotlin.runCatching {
                calculator.evalute(expression.toString())
            }.onFailure {
                Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
            }.onSuccess {
                expression = Expressions(mutableListOf(Operand(it.toDouble())))
                binding.textView.text = it
            }
        }
    }
}
