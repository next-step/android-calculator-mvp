package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter
    private lateinit var calculationMemoryAdapter: CalculationMemoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        binding.button0.setOnClickListener { presenter.addOperand(0) }
        binding.button1.setOnClickListener { presenter.addOperand(1) }
        binding.button2.setOnClickListener { presenter.addOperand(2) }
        binding.button3.setOnClickListener { presenter.addOperand(3) }
        binding.button4.setOnClickListener { presenter.addOperand(4) }
        binding.button5.setOnClickListener { presenter.addOperand(5) }
        binding.button6.setOnClickListener { presenter.addOperand(6) }
        binding.button7.setOnClickListener { presenter.addOperand(7) }
        binding.button8.setOnClickListener { presenter.addOperand(8) }
        binding.button9.setOnClickListener { presenter.addOperand(9) }
        binding.buttonPlus.setOnClickListener { presenter.addOperator(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { presenter.addOperator(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { presenter.addOperator(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { presenter.addOperator(Operator.Divide) }
        binding.buttonDelete.setOnClickListener { presenter.removeLast() }
        binding.buttonEquals.setOnClickListener { presenter.calculateExpression() }
        binding.buttonMemory.setOnClickListener { presenter.showCalculationMemory() }

        calculationMemoryAdapter = CalculationMemoryAdapter()
        binding.recyclerView.adapter = calculationMemoryAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showIncompleteExpressionToast() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun showCalculationMemory(calculationRecordList: List<Pair<String, Int>>) {

        if (binding.recyclerView.visibility == View.VISIBLE) {
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.recyclerView.visibility = View.VISIBLE
            calculationMemoryAdapter.submitList(calculationRecordList)
        }

    }
}
