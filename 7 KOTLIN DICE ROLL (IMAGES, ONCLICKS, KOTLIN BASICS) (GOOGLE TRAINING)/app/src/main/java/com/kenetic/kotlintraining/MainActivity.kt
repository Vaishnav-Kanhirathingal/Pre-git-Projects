package com.kenetic.kotlintraining

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diceImage1 : ImageView = findViewById(R.id.diceImageView1)
        diceImage1.setImageResource(R.drawable.dice_1)
        val diceImage2 : ImageView = findViewById(R.id.diceImageView2)
        diceImage2.setImageResource(R.drawable.dice_2)

        val dice = DiceRoll(6)
        val rollButton : Button = findViewById(R.id.Calculate)
        rollButton.setOnClickListener {
            //roll - 1   roll - 1   roll - 1   roll - 1   roll - 1   roll - 1   roll - 1   roll - 1   roll - 1   roll - 1
            val diceRoll1 = dice.roll()
            val drawableDice1 = when(diceRoll1) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            diceImage1.setImageResource(drawableDice1)
            diceImage1.contentDescription = diceRoll1.toString()
            //roll - 2   roll - 2   roll - 2   roll - 2   roll - 2   roll - 2   roll - 2   roll - 2   roll - 2   roll - 2
            val diceRoll2 = dice.roll()
            val drawableDice2 = when(diceRoll2) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            diceImage2.setImageResource(drawableDice2)
            diceImage2.contentDescription = diceRoll2.toString()
        }
    }
}