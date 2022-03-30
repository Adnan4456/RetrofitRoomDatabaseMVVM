package com.example.retrofitwithmvvm.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitroomdatabasemvvm.MainActivity
import com.example.retrofitroomdatabasemvvm.db.QuoteDataBase
import com.example.retrofitroomdatabasemvvm.utils.NetworkUtils
import com.example.retrofitwithmvvm.api.QuoteService
import com.example.retrofitwithmvvm.models.QuoteList

//Repository will access QuoteService and this will access hit our API.
//Repository class manage our data from retrofit or Room database.
class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDataBase: QuoteDataBase,
    private val context: Context
) {

    private val quoteLiveData = MutableLiveData<QuoteList>()

    //it will access quoteLiveData Mutable
    //we will observ this data in our viewModels.
    val quotes: LiveData<QuoteList>
    get() = quoteLiveData

    //we will define a function.That viewModel will call
    //This function is calling our API internally.
    suspend fun getQuotes(page: Int ){

        //check internet is available then use Network fr data.Otherwise use Room database.
        if(NetworkUtils.isOnline(context)){

            val result = quoteService.getQuotes(page)
            //check result from API is not null
            if (result?.body() != null){ //result != null && result.body() !=null

                //Now store this reponse into database
                quoteDataBase.quoteDAO().addQuotes(result.body()!!.results)
                quoteLiveData.postValue(result.body())
                Log.d("Network","is connected")
            }

        }
        else{

            val quo = quoteDataBase.quoteDAO().getQuote()
            val quoteList = QuoteList(1,1,1,quo,1,1)
            quoteLiveData.postValue(quoteList)
            Log.d("Network","is not connected")
        }
    }

}