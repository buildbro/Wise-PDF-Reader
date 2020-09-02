package com.buildbrothers.wisepdfreader.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.buildbrothers.wisepdfreader.model.Document

@Dao
interface DocumentDao {

    @Query("SELECT * FROM my_pdf_docs ORDER BY lastRead DESC")
    fun getRecentDocs(): LiveData<List<Document>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(document: Document)

    @Query("DELETE FROM my_pdf_docs WHERE id = :id")
    suspend fun delete(id:Int)
}