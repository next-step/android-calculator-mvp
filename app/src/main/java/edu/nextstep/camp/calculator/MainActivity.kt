package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.ExpressionHistoryItem
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter
    private val calculatorHistoryAdapter by lazy { CalculatorHistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)

        initRecyclerView()

        binding.button0.setOnClickListener { presenter.addToExpression(0) }
        binding.button1.setOnClickListener { presenter.addToExpression(1) }
        binding.button2.setOnClickListener { presenter.addToExpression(2) }
        binding.button3.setOnClickListener { presenter.addToExpression(3) }
        binding.button4.setOnClickListener { presenter.addToExpression(4) }
        binding.button5.setOnClickListener { presenter.addToExpression(5) }
        binding.button6.setOnClickListener { presenter.addToExpression(6) }
        binding.button7.setOnClickListener { presenter.addToExpression(7) }
        binding.button8.setOnClickListener { presenter.addToExpression(8) }
        binding.button9.setOnClickListener { presenter.addToExpression(9) }
        binding.buttonPlus.setOnClickListener { presenter.addToExpression(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { presenter.addToExpression(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { presenter.addToExpression(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { presenter.addToExpression(Operator.Divide) }
        binding.buttonDelete.setOnClickListener { presenter.removeLastFromExpression() }
        binding.buttonEquals.setOnClickListener { presenter.calculateExpression() }
        binding.buttonMemory.setOnClickListener { presenter.toggleExpressionHistory() }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = calculatorHistoryAdapter
            itemAnimator = null
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun showExpression(rawExpression: String) {
        binding.textView.text = rawExpression
    }

    override fun openCalculationHistories(histories: List<ExpressionHistoryItem>) {
        binding.recyclerView.isVisible = true
        calculatorHistoryAdapter.data = histories
    }

    override fun closeCalculationHistories() {
        binding.recyclerView.isVisible = false
    }

    override fun showCalculationFailMessage() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
