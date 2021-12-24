package com.kenetic.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kenetic.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateTip.setOnClickListener { calcTip() }
        binding.roundUpTipTextView.text =
            getString(R.string.tip_amount, NumberFormat.getCurrencyInstance().format(0.0))
        //binding.costOfServiceEditText.setOnKeyListener{ view, keyCode, _ -> keyboardHandler(view,keyCode)}
    }


    private fun calcTip() {
        val serviceCost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        if (serviceCost == null) {
            binding.roundUpTipTextView.text =
                getString(R.string.tip_amount, NumberFormat.getCurrencyInstance().format(0.0))
            return
        }
        val percentOfTip: Double = when (binding.serviceRadioGroup.checkedRadioButtonId) {
            R.id.option_amazing_20_percent -> .2
            R.id.option_good_18_percent -> .18
            else -> .15
        }
        var cost = (1 + percentOfTip) * serviceCost
        if (binding.roundUpTipSwitch.isChecked) {
            cost = kotlin.math.ceil(cost)
        }
        binding.roundUpTipTextView.text =
            getString(R.string.tip_amount, NumberFormat.getCurrencyInstance().format(cost))
    }

    /*
    private fun keyboardHandler(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
     */
}