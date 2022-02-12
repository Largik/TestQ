package com.test.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import io.realm.Realm

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        setupActionBar()

        showListFromDB()
    }

    private fun setupActionBar() {
        supportActionBar?.apply{
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)

            title = "Избранное"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun loadFromDB ():List<Cat>{
        val realm = Realm.getDefaultInstance()

        return realm.where(Cat::class.java).findAll()
    }

    private fun showListFromDB (){
        val cats = loadFromDB()
        setList(cats)
    }

    private fun setList(cats: List<Cat>){
        val recycler = findViewById<RecyclerView>(R.id.recyclerId)

        val adapter = CatAdapter(cats)
        recycler.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
    }
}