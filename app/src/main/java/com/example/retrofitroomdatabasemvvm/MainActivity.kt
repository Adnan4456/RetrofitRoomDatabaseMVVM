package com.example.retrofitroomdatabasemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitroomdatabasemvvm.db.QuoteDataBase
import com.example.retrofitwithmvvm.api.QuoteService
import com.example.retrofitwithmvvm.api.RetrofitHelper
import com.example.retrofitwithmvvm.repository.QuoteRepository
import com.example.retrofitwithmvvm.viewmodels.MainViewModel
import com.example.retrofitwithmvvm.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var quoteRepository: QuoteRepository

    val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
    val database = QuoteDataBase.getDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quoteRepository = QuoteRepository(quoteService, database, this)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(quoteRepository))
            .get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, {

            Log.d("MainActivity",it.results.toString())
        })
    }
}