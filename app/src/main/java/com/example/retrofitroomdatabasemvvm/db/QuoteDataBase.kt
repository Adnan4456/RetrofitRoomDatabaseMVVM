package com.example.retrofitroomdatabasemvvm.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitwithmvvm.api.RetrofitHelper
import com.example.retrofitwithmvvm.models.QuoteResult

@Database(entities = [QuoteResult::class] , version = 1)
abstract  class QuoteDataBase :RoomDatabase(){

    abstract fun quoteDAO(): QuoteDao

    companion object{
        @Volatile
        private var INSTANCE: QuoteDataBase? = null

        fun getDatabase(context: Context):QuoteDataBase{
            if (INSTANCE == null){

                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        QuoteDataBase::class.java,
                        "quoteDB")
                        .build()
                    Log.d("Database","Object is created")
                }

            }
            else
            {
                Log.d("Database","Object is not null")
            }
            return INSTANCE!!
        }
    }
}