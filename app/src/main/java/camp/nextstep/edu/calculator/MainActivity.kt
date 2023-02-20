package camp.nextstep.edu.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.calculator.Calculator
import com.nextstep.calculator.Expression
import com.nextstep.calculator.Operator.* // ktlint-disable no-wildcard-imports

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculator by lazy { Calculator() }
    private val expression by lazy { Expression() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 수 입력
        binding.button0.setOnClickListener {
            expression.checkInput("0")
            updateInput()
        }
        binding.button1.setOnClickListener {
            expression.checkInput("1")
            updateInput()
        }
        binding.button2.setOnClickListener {
            expression.checkInput("2")
            updateInput()
        }
        binding.button3.setOnClickListener {
            expression.checkInput("3")
            updateInput()
        }
        binding.button4.setOnClickListener {
            expression.checkInput("4")
            updateInput()
        }
        binding.button5.setOnClickListener {
            expression.checkInput("5")
            updateInput()
        }
        binding.button6.setOnClickListener {
            expression.checkInput("6")
            updateInput()
        }
        binding.button7.setOnClickListener {
            expression.checkInput("7")
            updateInput()
        }
        binding.button8.setOnClickListener {
            expression.checkInput("8")
            updateInput()
        }
        binding.button9.setOnClickListener {
            expression.checkInput("9")
            updateInput()
        }

        // 사칙연산 기호 입력
        binding.buttonPlus.setOnClickListener {
            expression.checkInput(PLUS.operator)
            updateInput()
        }
        binding.buttonMinus.setOnClickListener {
            expression.checkInput(MINUS.operator)
            updateInput()
        }
        binding.buttonMultiply.setOnClickListener {
            expression.checkInput(MULTIPLY.operator)
            updateInput()
        }
        binding.buttonDivide.setOnClickListener {
            expression.checkInput(DIVIDE.operator)
            updateInput()
        }

        // 입력된 문자 1개 지우기
        binding.buttonDelete.setOnClickListener {
            expression.removeInput()
            updateInput()
        }

        // 결과값 출력
        binding.buttonEquals.setOnClickListener {
            val expression = expression.getInputExpression()
            val result = calculator.calculate(expression)

            binding.textView.text = result.toString()
        }
    }

    private fun updateInput() {
        binding.textView.text = expression.getInputExpression()
    }
}
