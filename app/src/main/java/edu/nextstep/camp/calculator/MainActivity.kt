package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.ExpressionHistoryItem
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPresenter: MainContract.Presenter
    private val calculatorHistoryAdapter by lazy { CalculatorHistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter(this)
        initView()
        initListener()
    }

    private fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = calculatorHistoryAdapter
        }
    }

    private fun initListener() {
        binding.button0.setOnClickListener { mainPresenter.addToExpression(0) }
        binding.button1.setOnClickListener { mainPresenter.addToExpression(1) }
        binding.button2.setOnClickListener { mainPresenter.addToExpression(2) }
        binding.button3.setOnClickListener { mainPresenter.addToExpression(3) }
        binding.button4.setOnClickListener { mainPresenter.addToExpression(4) }
        binding.button5.setOnClickListener { mainPresenter.addToExpression(5) }
        binding.button6.setOnClickListener { mainPresenter.addToExpression(6) }
        binding.button7.setOnClickListener { mainPresenter.addToExpression(7) }
        binding.button8.setOnClickListener { mainPresenter.addToExpression(8) }
        binding.button9.setOnClickListener { mainPresenter.addToExpression(9) }
        binding.buttonPlus.setOnClickListener { mainPresenter.addToExpression(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { mainPresenter.addToExpression(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { mainPresenter.addToExpression(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { mainPresenter.addToExpression(Operator.Divide) }
        binding.buttonDelete.setOnClickListener { mainPresenter.removeLastFromExpression() }
        binding.buttonEquals.setOnClickListener { mainPresenter.calculateExpression() }
        binding.buttonMemory.setOnClickListener {
            val isShow = binding.recyclerView.isGone
            mainPresenter.toggleExpressionHistory(isShow)
        }
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun openHistory(expressions: List<ExpressionHistoryItem>) {
        calculatorHistoryAdapter.submitList(expressions)
        binding.recyclerView.isVisible = true
    }

    override fun closeHistory() {
        binding.recyclerView.isVisible = false
    }
}
