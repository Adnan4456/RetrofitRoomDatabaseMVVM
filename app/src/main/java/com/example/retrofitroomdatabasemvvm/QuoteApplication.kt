package com.example.retrofitroomdatabasemvvm

import android.app.Application
import com.example.retrofitroomdatabasemvvm.db.QuoteDataBase
import com.example.retrofitwithmvvm.api.QuoteService
import com.example.retrofitwithmvvm.api.RetrofitHelper
import com.example.retrofitwithmvvm.repository.QuoteRepository

class QuoteApplication: Application() {

    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    fun initialize()
    {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDataBase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, this)

    }
}