package com.example.blink22.roomwordsample

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewWordActivity : AppCompatActivity() {
    companion object {
        val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
    private lateinit var mWordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        mWordEditText = findViewById(R.id.edit_word)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener{ view ->
            val replyIntent = Intent()
            if(TextUtils.isEmpty(mWordEditText.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                val word = mWordEditText.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
