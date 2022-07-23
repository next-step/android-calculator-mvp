package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.view.MemoryRecyclerViewAdapter

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        initView()
    }

    private fun initView() {
        initRecyclerView()
        setClickListener()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = MemoryRecyclerViewAdapter(presenter.getItemList())
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setClickListener() {
        binding.button0.setOnClickListener {
            presenter.onClickNumberButton(0)
        }
        binding.button1.setOnClickListener {
            presenter.onClickNumberButton(1)
        }
        binding.button2.setOnClickListener {
            presenter.onClickNumberButton(2)
        }
        binding.button3.setOnClickListener {
            presenter.onClickNumberButton(3)
        }
        binding.button4.setOnClickListener {
            presenter.onClickNumberButton(4)
        }
        binding.button5.setOnClickListener {
            presenter.onClickNumberButton(5)
        }
        binding.button6.setOnClickListener {
            presenter.onClickNumberButton(6)
        }
        binding.button7.setOnClickListener {
            presenter.onClickNumberButton(7)
        }
        binding.button8.setOnClickListener {
            presenter.onClickNumberButton(8)
        }
        binding.button9.setOnClickListener {
            presenter.onClickNumberButton(9)
        }
        binding.buttonPlus.setOnClickListener {
            presenter.onClickOperandButton(PLUS)
        }
        binding.buttonMinus.setOnClickListener {
            presenter.onClickOperandButton(MINUS)
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.onClickOperandButton(MULTIPLY)
        }
        binding.buttonDivide.setOnClickListener {
            presenter.onClickOperandButton(DIVIDE)
        }
        binding.buttonDelete.setOnClickListener {
            presenter.onClickDeleteButton()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.onClickEqualButton()
        }

        binding.buttonMemory.setOnClickListener {
            presenter.onClickMemoryButton()
        }
    }

    override fun setResultTextView(text: String) {
        binding.textView.text = text
    }

    override fun showToastIncompleteExpression() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    override fun showExpressionMemoryView() {
        binding.recyclerView.visibility = View.VISIBLE
        val adapter = binding.recyclerView.adapter as MemoryRecyclerViewAdapter
        adapter.appendItems(presenter.getItemList())
    }

    override fun hideExpressionMemoryView() {
        binding.recyclerView.visibility = View.GONE
    }

    companion object {
        const val PLUS = "+"
        const val MINUS = "-"
        const val MULTIPLY = "*"
        const val DIVIDE = "/"
    }
}
