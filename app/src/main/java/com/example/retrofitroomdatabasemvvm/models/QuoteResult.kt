package com.example.retrofitwithmvvm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class QuoteResult(

    @PrimaryKey(autoGenerate = true)
    public val quoteId:Int,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
)