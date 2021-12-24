package com.kenetic.affermationsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.kenetic.affermationsapp.adapter.ItemAdapter
import com.kenetic.affermationsapp.data.DataSource

class MainActivity : AppCompatActivity() {

    //lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myDataSet = DataSource().loadAffirmations()
        //stores list of quotes

        val recyclerView = findViewById<RecyclerView>(R.id.recyler_view)
        //stores recycler view

        recyclerView.adapter = ItemAdapter(this, myDataSet)
        //setting up an adapter
        recyclerView.setHasFixedSize(true)

    }
}