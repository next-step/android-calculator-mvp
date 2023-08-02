package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.edu.domain.Calculator
import com.nextstep.edu.domain.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val expression = Expression()
    private val calculator = Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListener()
    }

    private fun initClickListener() {

        binding.button0.setOnClickListener { onClickButtons("0") }
        binding.button1.setOnClickListener { onClickButtons("1") }
        binding.button2.setOnClickListener { onClickButtons("2") }
        binding.button3.setOnClickListener { onClickButtons("3") }
        binding.button4.setOnClickListener { onClickButtons("4") }
        binding.button5.setOnClickListener { onClickButtons("5") }
        binding.button6.setOnClickListener { onClickButtons("6") }
        binding.button7.setOnClickListener { onClickButtons("7") }
        binding.button8.setOnClickListener { onClickButtons("8") }
        binding.button9.setOnClickListener { onClickButtons("9") }

        binding.buttonPlus.setOnClickListener { onClickButtons("+") }
        binding.buttonMinus.setOnClickListener { onClickButtons("-") }
        binding.buttonDivide.setOnClickListener { onClickButtons("*") }
        binding.buttonMultiply.setOnClickListener { onClickButtons("÷") }

        binding.buttonDelete.setOnClickListener {
            expression.removeLatestExpression()
            setResultText()
        }
        binding.buttonMemory.setOnClickListener {
            expression.resetMemory()
            setResultText()
        }

        binding.buttonEquals.setOnClickListener {
            onResultEquals()
        }
    }

    private fun onClickButtons(text: String) {
        expression.setExpressions(text)
        setResultText()
    }

    private fun onResultEquals() {
        runCatching {
            calculator.evaluate(expression.expressions)
        }.onSuccess {
            setResultText()
        }.onFailure {
            Toast.makeText(this@MainActivity, getString(R.string.incomplete_expression), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setResultText() {
        binding.textView.text = expression.expressions
    }
}
