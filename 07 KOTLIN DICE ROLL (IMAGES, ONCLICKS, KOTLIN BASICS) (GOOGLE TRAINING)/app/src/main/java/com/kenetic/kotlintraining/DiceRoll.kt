package com.kenetic.kotlintraining

class DiceRoll(private val sides: Int) {
    //var diceImage : ImageView = findV
    fun roll(): Int {
        return ((1..sides).random())
    }
}