package lt.vu.wordcounter

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import lt.vu.wordcounter.ui.theme.WordCounterTheme


class MainActivity : ComponentActivity() {
    var wordCounterProcessor: WordCounterProcessor = WordCounterProcessor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordCounterTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    initContent()
                }
            }
        }
    }

    fun initContent()
    {
        setContentView(R.layout.main_layout)
        prepareSpinnerElements()
        prepareCalulationButtonAction()
    }

    fun prepareSpinnerElements()
    {
        var spinner = findViewById<Spinner>(R.id.calculationTypeSpinner)
        var items = WordCounterProcessor.getAvailableTypesData(this)

        val adapter = object : ArrayAdapter<WordCounterCalculationTypeData>(this, android.R.layout.simple_spinner_item, items) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val item = getItem(position)
                (view as TextView).text = item?.name
                return view
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val item = getItem(position)
                (view as TextView).text = item?.name
                return view
            }
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = adapter.getItem(position)
                wordCounterProcessor.setType(selectedItem?.type)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun prepareCalulationButtonAction()
    {
        findViewById<Button>(R.id.calculateButton)
            .setOnClickListener {
                val inputEditText = findViewById<View>(R.id.inputTextField) as EditText
                val resultTextView = findViewById<View>(R.id.resultText) as TextView
                wordCounterProcessor.setText(inputEditText.text.toString())

                try {
                    var result = wordCounterProcessor.calculate(this)
                    resultTextView.text = result.formatResult()
                }
                catch (ex: Exception)
                {
                    Toast.makeText(this, ex.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
    }
}