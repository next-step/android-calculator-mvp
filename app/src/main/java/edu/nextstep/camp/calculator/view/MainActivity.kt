package edu.nextstep.camp.calculator.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edu.nextstep.camp.calculator.MainContract
import edu.nextstep.camp.calculator.R
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter
    private val recordAdapter by lazy { RecordAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)
        initRecyclerView()

        clickButtonListener()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = recordAdapter
        }
    }

    private fun clickButtonListener() {
        binding.button0.setOnClickListener { appendOperand(getString(R.string.calculator_0)) }
        binding.button1.setOnClickListener { appendOperand(getString(R.string.calculator_1)) }
        binding.button2.setOnClickListener { appendOperand(getString(R.string.calculator_2)) }
        binding.button3.setOnClickListener { appendOperand(getString(R.string.calculator_3)) }
        binding.button4.setOnClickListener { appendOperand(getString(R.string.calculator_4)) }
        binding.button5.setOnClickListener { appendOperand(getString(R.string.calculator_5)) }
        binding.button6.setOnClickListener { appendOperand(getString(R.string.calculator_6)) }
        binding.button7.setOnClickListener { appendOperand(getString(R.string.calculator_7)) }
        binding.button8.setOnClickListener { appendOperand(getString(R.string.calculator_8)) }
        binding.button9.setOnClickListener { appendOperand(getString(R.string.calculator_9)) }

        binding.buttonPlus.setOnClickListener { appendOperator(getString(R.string.calculator_plus)) }
        binding.buttonMinus.setOnClickListener { appendOperator(getString(R.string.calculator_minus)) }
        binding.buttonDivide.setOnClickListener { appendOperator(getString(R.string.calculator_divide)) }
        binding.buttonMultiply.setOnClickListener { appendOperator(getString(R.string.calculator_multiply)) }
        binding.buttonDelete.setOnClickListener { deleteLastElement() }
        binding.buttonEquals.setOnClickListener { calculateStatement() }
        binding.buttonMemory.setOnClickListener { toggleMemoryView() }
    }

    private fun appendOperand(operand: String) {
        presenter.appendOperand(binding.textView.text.toString(), operand)
    }

    private fun appendOperator(operator: String) {
        presenter.appendOperator(binding.textView.text.toString(), operator)
    }

    private fun deleteLastElement() {
        presenter.deleteLastElement(binding.textView.text.toString())
    }

    private fun calculateStatement() {
        presenter.calculate(binding.textView.text.toString())
    }

    override fun showExpression(expression: String?) {
        binding.textView.text = expression
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun toggleMemoryView() {
        binding.recyclerView.isVisible = !binding.recyclerView.isVisible
    }

    override fun saveAddedStatement() {
        recordAdapter.addStatement(presenter.recordStatementList[0])
    }
}