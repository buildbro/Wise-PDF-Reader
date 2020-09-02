package com.buildbrothers.wisepdfreader.database

import androidx.lifecycle.LiveData
import com.buildbrothers.wisepdfreader.model.Document
import javax.inject.Inject

class DocumentRepository @Inject constructor(private val documentDao: DocumentDao) {

    val recentDocuments: LiveData<List<Document>> = documentDao.getRecentDocs()

    suspend fun insert(document: Document) {
        documentDao.insert(document)
    }
}