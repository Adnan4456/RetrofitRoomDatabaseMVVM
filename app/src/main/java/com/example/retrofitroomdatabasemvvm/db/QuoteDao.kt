package com.example.retrofitroomdatabasemvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.retrofitwithmvvm.models.QuoteResult

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quote:List<QuoteResult>)

    @Query("SELECT * FROM quote")
    suspend fun getQuote() : List<QuoteResult>
}