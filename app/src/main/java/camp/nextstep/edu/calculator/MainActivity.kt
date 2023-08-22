package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.CalculationOperator
import camp.nextstep.edu.domain.Evaluator
import camp.nextstep.edu.domain.ExpressionHandler

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var expressionHandler: ExpressionHandler
    private val evaluator = Evaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        expressionHandler = ExpressionHandler { binding.textView.text = it }

        setOnNumberClickListener()
        setOnOperatorClickListener()
        setOnDeleteClickListener()
        setOnCalculateClickListener()
    }

    private fun setOnNumberClickListener() {
        with(binding) {
            button1.setOnClickListener { expressionHandler.addInputValue("1") }
            button0.setOnClickListener { expressionHandler.addInputValue("0") }
            button2.setOnClickListener { expressionHandler.addInputValue("2") }
            button3.setOnClickListener { expressionHandler.addInputValue("3") }
            button4.setOnClickListener { expressionHandler.addInputValue("4") }
            button5.setOnClickListener { expressionHandler.addInputValue("5") }
            button6.setOnClickListener { expressionHandler.addInputValue("6") }
            button7.setOnClickListener { expressionHandler.addInputValue("7") }
            button8.setOnClickListener { expressionHandler.addInputValue("8") }
            button9.setOnClickListener { expressionHandler.addInputValue("9") }
        }
    }

    private fun setOnOperatorClickListener() {
        with(binding) {
            buttonPlus.setOnClickListener { expressionHandler.addInputValue(" ${CalculationOperator.PLUS.symbol} ") }
            buttonMinus.setOnClickListener { expressionHandler.addInputValue(" ${CalculationOperator.MINUS.symbol} ") }
            buttonDivide.setOnClickListener { expressionHandler.addInputValue(" ${CalculationOperator.DIVIDE.symbol} ") }
            buttonMultiply.setOnClickListener { expressionHandler.addInputValue(" ${CalculationOperator.MULTIPLY.symbol} ") }
        }
    }

    private fun setOnDeleteClickListener() {
        binding.buttonDelete.setOnClickListener {
            expressionHandler.deleteLast()
        }
    }

    private fun setOnCalculateClickListener() {
        binding.buttonEquals.setOnClickListener {
            showResultOrThrow()
        }
    }

    private fun showResultOrThrow() {
        runCatching {
            evaluator.evaluate(expressionHandler.expression)
        }.onSuccess {
            expressionHandler.setEvaluationResult(it.toString())
        }.onFailure {
            Toast.makeText(
                this@MainActivity,
                getString(R.string.incomplete_expression),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
