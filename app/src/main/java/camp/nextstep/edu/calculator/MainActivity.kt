package camp.nextstep.edu.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.edu.domain.CalculationExpression
import com.nextstep.edu.domain.Calculator
import com.nextstep.edu.domain.Operator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var calculationExpression: CalculationExpression = CalculationExpression()
    private val calculate: Calculator = Calculator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { appendExpression(0) }
        binding.button1.setOnClickListener { appendExpression(1) }
        binding.button2.setOnClickListener { appendExpression(2) }
        binding.button3.setOnClickListener { appendExpression(3) }
        binding.button4.setOnClickListener { appendExpression(4) }
        binding.button5.setOnClickListener { appendExpression(5) }
        binding.button6.setOnClickListener { appendExpression(6) }
        binding.button7.setOnClickListener { appendExpression(7) }
        binding.button8.setOnClickListener { appendExpression(8) }
        binding.button9.setOnClickListener { appendExpression(9) }

        binding.buttonPlus.setOnClickListener { appendExpression(Operator.ADDITION) }
        binding.buttonMinus.setOnClickListener { appendExpression(Operator.SUBTRACT) }
        binding.buttonMultiply.setOnClickListener { appendExpression(Operator.MULTIPLY) }
        binding.buttonDivide.setOnClickListener { appendExpression(Operator.DIVIDE) }
    }

    private fun appendExpression(operand: Int) {
        calculationExpression.add(operand)
        binding.textView.text = calculationExpression.toString()
    }

    private fun appendExpression(operator: Operator) {
        calculationExpression.add(operator)
        binding.textView.text = calculationExpression.toString()
    }
}
