package com.kenetic.android_studio_testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var algo : MutableList<String> = arrayListOf(" "," ")
    private var current = 0
    private var decimalPlace = false
    private var curSym = ' '
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //start number   start number   start number   start number   start number   start number   start number   start number   start number   start number
    fun num1 (view: View){
        algo[current] += "1"
        setView()
    }
    fun num2 (view: View){
        algo[current] += "2"
        setView()
    }
    fun num3 (view: View){
        algo[current] += "3"
        setView()
    }
    fun num4 (view: View){
        algo[current] += "4"
        setView()
    }
    fun num5 (view: View){
        algo[current] += "5"
        setView()
    }
    fun num6 (view: View){
        algo[current] += "6"
        setView()
    }
    fun num7 (view: View){
        algo[current] += "7"
        setView()
    }
    fun num8 (view: View){
        algo[current] += "8"
        setView()
    }
    fun num9 (view: View){
        algo[current] += "9"
        setView()
    }
    fun num0 (view: View){
        algo[current] += "0"
        setView()
    }

    fun decimalPoint (view: View){
        if (!decimalPlace){
            algo[current] += "."
            decimalPlace = true
            setView()
        }
    }
    fun plus (view: View){
        eval()
        curSym = '+'
        decimalPlace = false
        current = 1
        setView()
    }
    fun minus (view: View){
        eval()
        curSym = '-'
        decimalPlace = false
        current = 1
        setView()
    }
    fun multiply (view: View){
        eval()
        curSym = '*'
        decimalPlace = false
        current = 1
        setView()
    }
    fun divide (view: View){
        eval()
        curSym = '/'
        decimalPlace = false
        current = 1
        setView()
    }
    fun percentOf (view: View){
        eval()
        curSym = '%'
        decimalPlace = false
        current = 1
    }
    fun equal (view: View){
        eval()
        curSym = ' '
        setView()
    }
    fun clearAll (view: View){
        algo[0] = " "
        algo[1] = " "
        current = 0
        decimalPlace = false
        curSym = ' '
        setView()
    }
    fun deleteOne (view: View) {
        if (algo[current].length == 1){
            algo[current] = "0"
        }
        else if (algo[current][algo[current].length-1] == '.'){     //checking last place value
            algo[current] = algo[current].dropLast(1)
            decimalPlace = false
        }
        else {
            algo[current] = algo[current].dropLast(1)
        }
        setView()
    }
    //closeNumber  close Number  close Number  close Number  close Number  close Number  close Number  close Number  close Number  close Number  close Number

    private fun eval (){
        if (curSym == '+'){
            algo[0] = (algo[0].toFloat()+algo[1].toFloat()).toString()
            algo[1] = " "
        }
        if (curSym == '-'){
            algo[0] = (algo[0].toFloat()-algo[1].toFloat()).toString()
            algo[1] = " "
        }
        if (curSym == '*'){
            algo[0] = (algo[0].toFloat()*algo[1].toFloat()).toString()
            algo[1] = " "
        }
        if (curSym == '/'){
            algo[0] = (algo[0].toFloat()/algo[1].toFloat()).toString()
            algo[1] = " "
        }
        if (curSym == '%'){
            algo[0] = ((algo[0].toFloat()*algo[1].toFloat())/100).toString()
            algo[1] = " "
        }
    }
    fun setView(){
        var resTxt : TextView = findViewById(R.id.TextDisp)
        if (current == 0){
            resTxt.text = algo[0] + curSym.toString()
        }
        else{
            resTxt.text = algo[0]+curSym.toString()+"\n"+algo[1]
        }
    }
}