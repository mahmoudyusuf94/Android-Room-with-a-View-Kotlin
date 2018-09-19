package com.example.blink22.roomwordsample

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.blink22.roomwordsample.Room.Word
import com.example.blink22.roomwordsample.Room.WordDao
import com.example.blink22.roomwordsample.Room.WordRoomDatabase

class WordRepo{

    lateinit private var mWordDao: WordDao
    lateinit private var mAllWords: LiveData<List<Word>>

    constructor(application: Application){
        val db: WordRoomDatabase = WordRoomDatabase.getDatabase(application)
        mWordDao = db.wordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>>{
        return mAllWords
    }

    fun insert(word: Word){
        InsertAsyncTask(mWordDao).execute(word)
    }
}


private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) : AsyncTask<Word, Void, Void>() {

    override fun doInBackground(vararg params: Word): Void? {
        mAsyncTaskDao.insert(params[0])
        return null
    }
}