package com.example.retrofitwithmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitwithmvvm.models.QuoteList
import com.example.retrofitwithmvvm.repository.QuoteRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) :ViewModel() {

    //when our viewmodel loaded object created. we call repository
    init {

        viewModelScope.launch {
            repository.getQuotes(1)
        }
    }

    val  quotes: LiveData<QuoteList>
    get()= repository.quotes
}