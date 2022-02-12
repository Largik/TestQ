package com.test.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm

class DetailActivity : AppCompatActivity() {

    companion object{
        const val CAT_FACT_TEXT_TAG = "cat_fact_text"
    }

    private val urlImg = "https://aws.random.cat/meow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val button = findViewById<Button>(R.id.buttonId)
        setText()

        setupActionBar()

        button.setOnClickListener{
            val cat = Cat()
            cat.text = intent?.extras?.getString(CAT_FACT_TEXT_TAG).toString()
            saveIntoDB(cat)
        }
    }

    private fun setupActionBar() {
        supportActionBar?.apply{
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)

            title = "Кошка"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun saveIntoDB(cat: Cat){
        val realm = Realm.getDefaultInstance()

        realm.beginTransaction()
        realm.copyToRealm(cat)
        realm.commitTransaction()
    }

    private fun setText() {
        val text = intent?.extras?.getString(CAT_FACT_TEXT_TAG)

        val textView = findViewById<TextView>(R.id.DetailTextViewId)
        textView.text = text
    }
}