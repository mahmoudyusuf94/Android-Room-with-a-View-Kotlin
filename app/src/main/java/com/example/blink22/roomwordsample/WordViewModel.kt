package com.example.blink22.roomwordsample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.blink22.roomwordsample.Room.Word

class WordViewModel : AndroidViewModel{

    private lateinit var mRepo: WordRepo
    private lateinit var mAllWords: LiveData<List<Word>>

    constructor(application: Application): super(application){
        mRepo = WordRepo(application)
        mAllWords = mRepo.getAllWords()
    }

    fun getAllWords() = mAllWords

    fun insert(word: Word){
        mRepo.insert(word)
    }

}