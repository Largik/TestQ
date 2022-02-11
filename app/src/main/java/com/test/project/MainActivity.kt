package com.test.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    /*private lateinit var binding: ResultProfileBinding*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val view = binding.root
        setContentView(view)*/

        setList()
    }
    
    private fun setList(){
        val cats = listOf(
            "cat1",
            "cat2",
            "cat3",
            "cat4",
            "cat5",
            "cat6",
            "cat7",
            "cat8",
            "cat9",
            "cat10",
            "cat11",
            "cat12",
            "cat13",
            "cat14",
            "cat15",
            "cat16",
            "cat17",
            "cat18",
            "cat19",
            "cat20"
        )

        val recycler = findViewById<RecyclerView>(R.id.recyclerId)

        val adapter = CatAdapter(cats)
        recycler.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
    }
}