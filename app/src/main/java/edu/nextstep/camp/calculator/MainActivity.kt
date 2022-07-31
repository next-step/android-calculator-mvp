package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringExpressionState


class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private val presenter: MainContract.Presenter by lazy {
        MainPresenter(
            view = this,
            initialState = StringExpressionState.of(binding.textView.text.toString())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewBinding()
        initButtons()
    }

    override fun setCalculationResult(result: Operand) {
        binding.textView.text = result.value.toString()
    }

    override fun calculationFailed() {
        Toast
            .makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT)
            .show()
    }

    override fun setExpression(state: StringExpressionState) {
        binding.textView.text = state.toString()
    }

    override fun showRecords(records: List<StringExpressionState>) {
        binding.recyclerView.isVisible = true
    }

    override fun closeRecords() {
        binding.recyclerView.isVisible = false
    }

    private fun initButtons() {
        initOperandButtons()
        initOperatorButtons()
        initDeleteButton()
        initEqualsButton()
        initRecordsButton()
    }

    private fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initOperandButtons() {
        (0..9).forEach { number ->
            val viewId = resources.getIdentifier("$BUTTON_VIEW_PREFIX$number", "id", packageName)
            val button = findViewById(viewId) as? Button ?: return@forEach
            setOperandButtonListener(button, Operand(number))
        }
    }

    private fun setOperandButtonListener(button: Button, operand: Operand) {
        button.setOnClickListener {
            presenter.addElement(operand)
        }
    }

    private fun initOperatorButtons() {
        listOf(
            binding.buttonPlus to Operator.PLUS,
            binding.buttonMinus to Operator.MINUS,
            binding.buttonMultiply to Operator.MULTIPLY,
            binding.buttonDivide to Operator.DIVIDE,
        ).forEach { (button, operator) ->
            setOperatorButtonListener(
                button = button,
                operator = operator
            )
        }
    }

    private fun setOperatorButtonListener(button: Button, operator: Operator) {
        button.setOnClickListener {
            presenter.addElement(operator)
        }
    }

    private fun initDeleteButton() {
        binding.buttonDelete.setOnClickListener {
            presenter.removeElement()
        }
    }

    private fun initEqualsButton() {
        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }

    private fun initRecordsButton() {
        binding.buttonMemory
    }

    companion object {
        private const val BUTTON_VIEW_PREFIX = "button"
    }
}
