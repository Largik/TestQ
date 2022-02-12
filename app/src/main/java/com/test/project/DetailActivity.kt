package com.test.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import io.realm.Realm
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {

    companion object{
        const val CAT_FACT_TEXT_TAG = "cat_fact_text"
    }

    private val urlImg = "https://aws.random.cat/meow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val button = findViewById<Button>(R.id.buttonId)

        setText(button)

        setupActionBar()

        val queue = Volley.newRequestQueue(this)
        getImageFromServer(queue)

        button.setOnClickListener{
            val cat = Cat()
            cat.text = intent?.extras?.getString(CAT_FACT_TEXT_TAG).toString()
            requestIntoDB(cat, button)
        }
    }

    private fun setupActionBar() {
        supportActionBar?.apply{
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)

            title = "Кошка"
        }
    }

    private fun getImageFromServer(queue: RequestQueue) {
        val stringRequest = StringRequest(
            Request.Method.GET,
            urlImg,
            { response ->
                takeImageUrl(response)
            },
            {
                Toast.makeText(this, "Loading Error", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(stringRequest)
    }

    private fun takeImageUrl(json: String) {
        val file = JSONObject(json)
        val urlText = file.getString("file")

        val imageView = findViewById<ImageView>(R.id.imageViewId)

        Glide.with(this).load(urlText).into(imageView)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun requestIntoDB(cat: Cat, button: Button){
        val realm = Realm.getDefaultInstance()

        realm.beginTransaction()
        var change = realm.where(Cat::class.java).equalTo("text", cat.text).findFirst()
        if (change?.favorite == true) {
            button.text = "Добавить из избранного"
            cat.favorite = false
            change.deleteFromRealm();
        }
        else {
            button.text = "Удалить из избранного"
            cat.favorite = true
            realm.copyToRealm(cat)
        }
        realm.commitTransaction()
    }

    private fun setText(button: Button) {
        val text = intent?.extras?.getString(CAT_FACT_TEXT_TAG)

        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var change = realm.where(Cat::class.java).equalTo("text", text as String).findFirst()
        if (change?.favorite == true) {
            button.text = "Удалить из избранного"
        }
        else {
            button.text = "Добавить в избранное"
        }
        realm.commitTransaction()

        val textView = findViewById<TextView>(R.id.DetailTextViewId)
        textView.text = text
    }
}