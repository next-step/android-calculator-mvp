package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this)
    private val adapter = CalculateRecordAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        executeButtonClickAction()
    }

    private fun executeButtonClickAction() {

        binding.recyclerView.adapter = adapter

        binding.button0.setOnClickListener { presenter.inputNumber(0) }
        binding.button1.setOnClickListener { presenter.inputNumber(1) }
        binding.button2.setOnClickListener { presenter.inputNumber(2) }
        binding.button3.setOnClickListener { presenter.inputNumber(3) }
        binding.button4.setOnClickListener { presenter.inputNumber(4) }
        binding.button5.setOnClickListener { presenter.inputNumber(5) }
        binding.button6.setOnClickListener { presenter.inputNumber(6) }
        binding.button7.setOnClickListener { presenter.inputNumber(7) }
        binding.button8.setOnClickListener { presenter.inputNumber(8) }
        binding.button9.setOnClickListener { presenter.inputNumber(9) }

        binding.buttonPlus.setOnClickListener { presenter.inputOperator(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { presenter.inputOperator(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { presenter.inputOperator(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { presenter.inputOperator(Operator.Divide) }

        binding.buttonDelete.setOnClickListener { presenter.removeLastIndexData() }
        binding.buttonEquals.setOnClickListener { presenter.calculateInputValue() }

        binding.buttonMemory.setOnClickListener { presenter.clickCalculatorRecord(binding.recyclerView.isVisible) }
    }

    override fun showCalculateExpression(expressionStr: String) {
        binding.textView.text = expressionStr
    }

    override fun showCompletionOfExpressionDataMessage() {
        Toast.makeText(this, "수식을 다시 입력해주세요!", Toast.LENGTH_SHORT).show()
    }

    override fun loadRecordList(recordList: List<RecordData>) {
        adapter.setRecordItemList(recordList)
    }

    override fun showRecord() {
        presenter.loadCalculatorRecord()
        binding.recyclerView.visibility = View.VISIBLE
    }

    override fun hideRecord() {
        binding.recyclerView.visibility = View.GONE
    }

}
