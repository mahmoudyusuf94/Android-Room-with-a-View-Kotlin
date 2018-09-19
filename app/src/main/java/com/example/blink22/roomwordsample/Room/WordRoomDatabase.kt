package com.example.blink22.roomwordsample.Room

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase: RoomDatabase(){

    abstract fun wordDao() : WordDao

    companion object {
        private var instance: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {

            if(instance == null){
                synchronized(WordRoomDatabase::class){
                    if(instance == null){
                        instance = Room.databaseBuilder(context.applicationContext,
                                WordRoomDatabase::class.java,"word_database")
                                .addCallback(object: RoomDatabase.Callback() {
                                    override fun onOpen(db: SupportSQLiteDatabase) {
                                        super.onOpen(db)
                                        PopulateDbAsync(instance!!).execute()

                                    }
                                })
                                .build()
                    }
                }
            }
            return instance!!
        }
    }

    class PopulateDbAsync : AsyncTask<Void, Void, Unit>{

        private lateinit var mDao: WordDao

        constructor(db: WordRoomDatabase){
            mDao = db.wordDao()
        }

        override fun doInBackground(vararg p0: Void?){
            mDao.deleteAll()
            var word = Word("Hello")
            mDao.insert(word)
            word = Word("World")
            mDao.insert(word)
        }

    }

}