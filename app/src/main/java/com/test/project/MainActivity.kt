package com.test.project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import io.realm.Realm
import io.realm.RealmConfiguration
import org.json.JSONArray


class MainActivity : AppCompatActivity() {

    private val url = "https://cat-fact.herokuapp.com/facts"
    private val urlImg = "https://aws.random.cat/meow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayoutId)
        tabLayout.setScrollPosition(0, 1F, false)

        val favorite = findViewById<FloatingActionButton>(R.id.floatingActionButtonId)
        favorite.setOnClickListener() {
            val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
            startActivity(intent)
        }

        initRealm()

        val queue = Volley.newRequestQueue(this)
        getCatsFromServer(queue)
    }

    private fun getCatsFromServer(queue: RequestQueue){
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val catList = parseResponse(response)

                setList(catList)
            },
            {
                Toast.makeText(this,"Loading error", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(stringRequest)
    }

    private fun initRealm(){
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    private fun parseResponse(responseText: String):List<Cat>{

        val catList: MutableList<Cat> = mutableListOf()
        val jsonArray = JSONArray(responseText)

        for(index in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(index)
            val catText = jsonObject.getString("text")

            val cat = Cat()
            cat.text = catText

            catList.add(cat)
        }

        return  catList
    }

    private fun setList(cats: List<Cat>){
        val recycler = findViewById<RecyclerView>(R.id.recyclerId)

        val adapter = CatAdapter(cats)
        recycler.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
    }
}