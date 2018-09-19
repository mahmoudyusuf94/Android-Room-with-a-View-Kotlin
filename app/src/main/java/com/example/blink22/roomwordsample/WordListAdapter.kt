package com.example.blink22.roomwordsample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.blink22.roomwordsample.Room.Word

class WordListAdapter: RecyclerView.Adapter<WordListAdapter.WordViewHolder>{

    class WordViewHolder: RecyclerView.ViewHolder{
        internal lateinit var wordItemView: TextView

        constructor(itemView: View): super(itemView){
            wordItemView = itemView.findViewById(R.id.textView)
        }
    }

    private lateinit var mInflater: LayoutInflater
    private var mWords: List<Word>? = null

    constructor(context: Context){
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WordViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, p0, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mWords?.size ?: 0
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        mWords?.let{
            val current = it.get(position)
            holder.wordItemView.setText(current?.mWord)
        } ?: holder.wordItemView.setText("No Word")
    }

    fun setWords(words: List<Word>){
        mWords = words
        notifyDataSetChanged()
    }
}