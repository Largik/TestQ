package com.test.project

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatAdapter(private val cats: List<String>) : RecyclerView.Adapter<CatViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val textView = TextView(parent.context)
        textView.textSize = 24f
        textView.setPadding(32, 12, 32, 12)

        return  CatViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(cats.get(position))
    }
}

class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(cat: String){
        (itemView as TextView).text = cat
    }
}