package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.exception.ExpressionNotCompleteException
import edu.nextstep.camp.calculator.domain.model.EvaluationRecord
import edu.nextstep.camp.calculator.domain.model.OtherExpressionToken
import edu.nextstep.camp.calculator.domain.model.ExpressionToken
import edu.nextstep.camp.calculator.domain.model.Operand
import edu.nextstep.camp.calculator.domain.model.Operator

class MainActivity : AppCompatActivity(), UserInputActionReceiver, MainContract.View {
    override lateinit var presenter: MainContract.Presenter
    private lateinit var binding: ActivityMainBinding
    private val adapter: EvaluationHistoryAdapter by lazy { EvaluationHistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@MainActivity
            userInputActionReceiver = this@MainActivity
            recyclerView.adapter = this@MainActivity.adapter
        }

        presenter = MainPresenter(this)
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
            btn.id == R.id.buttonMemory -> presenter.toggleEvaluationHistory()
            else -> handleExceptions(IllegalArgumentException("Unknown Input"))
        }
    }

    private fun onExpressionTokenInput(expressionToken: ExpressionToken) {
        when (expressionToken) {
            is Operand -> presenter.addOperandToken(expressionToken)
            is Operator -> presenter.addOperatorToken(expressionToken)
            OtherExpressionToken.DEL -> presenter.delete()
            OtherExpressionToken.EQUALS -> presenter.evaluate()
            else -> handleExceptions(IllegalArgumentException("Unknown Token"))
        }
    }

    override fun handleExceptions(throwable: Throwable) {
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

    override fun showEvaluationHistory(history: List<EvaluationRecord>) {
        adapter.submitList(history)
        binding.recyclerView.isVisible = true
    }

    override fun hideEvaluationHistory() {
        binding.recyclerView.isVisible = false
    }

    override fun displayExpression(expression: String) {
        binding.textView.text = expression
    }
}
