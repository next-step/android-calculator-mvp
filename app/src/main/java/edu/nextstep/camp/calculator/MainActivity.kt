package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    private val historyListAdapter = HistoryListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = historyListAdapter

        presenter = MainPresenter(this)

        binding.button0.setOnClickListener {
            presenter.enterNumber(0)
        }
        binding.button1.setOnClickListener {
            presenter.enterNumber(1)
        }
        binding.button2.setOnClickListener {
            presenter.enterNumber(2)
        }
        binding.button3.setOnClickListener {
            presenter.enterNumber(3)
        }
        binding.button4.setOnClickListener {
            presenter.enterNumber(4)
        }
        binding.button5.setOnClickListener {
            presenter.enterNumber(5)
        }
        binding.button6.setOnClickListener {
            presenter.enterNumber(6)
        }
        binding.button7.setOnClickListener {
            presenter.enterNumber(7)
        }
        binding.button8.setOnClickListener {
            presenter.enterNumber(8)
        }
        binding.button9.setOnClickListener {
            presenter.enterNumber(9)
        }
        binding.buttonPlus.setOnClickListener {
            presenter.enterOperator(Operator.Plus)
        }
        binding.buttonMinus.setOnClickListener {
            presenter.enterOperator(Operator.Minus)
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.enterOperator(Operator.Multiply)
        }
        binding.buttonDivide.setOnClickListener {
            presenter.enterOperator(Operator.Divide)
        }
        binding.buttonDelete.setOnClickListener {
            presenter.removeLast()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }
        binding.buttonMemory.setOnClickListener {
            presenter.toggleHistory()
        }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showIncomplete() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun showHistory(historyList: List<History>) {
        historyListAdapter.submitList(historyList)
        binding.recyclerView.visibility = View.VISIBLE
        binding.textView.visibility = View.INVISIBLE
    }

    override fun hideHistory() {
        binding.recyclerView.visibility = View.GONE
        binding.textView.visibility = View.VISIBLE
    }
}
