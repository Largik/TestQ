package com.test.project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private val url = "https://cat-fact.herokuapp.com/facts"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val view = binding.root
        setContentView(view)*/

        val queue = Volley.newRequestQueue(this)
        getCatsFromServer(queue)
    }

    private fun getCatsFromServer(queue: RequestQueue){
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(this,"Loading error", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(stringRequest)
    }

    private fun setList(){
       /* val cats =*/

        val recycler = findViewById<RecyclerView>(R.id.recyclerId)
/*

        val adapter = CatAdapter(cats)
        recycler.adapter = adapter
*/

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
    }
}