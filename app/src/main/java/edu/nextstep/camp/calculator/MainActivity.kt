package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.contract.MainContract
import edu.nextstep.camp.calculator.domain.exception.ExpressionNotCompleteException
import edu.nextstep.camp.calculator.domain.model.OtherExpressionToken
import edu.nextstep.camp.calculator.domain.model.ExpressionToken
import kotlin.runCatching

class MainActivity : AppCompatActivity(), UserInputActionReceiver, MainContract.View {
    override lateinit var presenter: MainContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@MainActivity
            userInputActionReceiver = this@MainActivity
        }

        presenter = MainContract.MainPresenter(this)
    }

    override fun onReceiveUserInputAction(v: View) {
        kotlin.runCatching { v as Button }
            .onFailure { handleExceptions(IllegalArgumentException("Input action view type must be Button")) }
            .onSuccess { processInputButton(it) }
    }

    private fun processInputButton(btn: Button) {
        when {
            !btn.text.isNullOrBlank() -> onExpressionTokenInput(ExpressionToken.getFromValue(btn.text.toString()))
            btn.id == R.id.buttonDelete -> onExpressionTokenInput(OtherExpressionToken.DEL)
            else -> handleExceptions(IllegalArgumentException("Unknown Input"))
        }
    }

    private fun onExpressionTokenInput(expressionToken: ExpressionToken) {
        runCatching {
            presenter.addExpressionToken(expressionToken)
        }
            .onFailure {
                handleExceptions(it)
            }
    }

    private fun handleExceptions(throwable: Throwable) {
        when (throwable) {
            is ExpressionNotCompleteException -> {
                Toast.makeText(this, R.string.expression_not_complete_message, Toast.LENGTH_SHORT).show()
            }
            is ArithmeticException -> {
                Toast.makeText(this, R.string.div_by_zero_message, Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, R.string.unknown_error_message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun displayExpression(expression: String) {
        binding.textView.text = expression
    }
}
