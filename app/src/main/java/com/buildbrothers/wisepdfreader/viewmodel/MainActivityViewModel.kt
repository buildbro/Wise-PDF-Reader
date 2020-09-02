package com.buildbrothers.wisepdfreader.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buildbrothers.wisepdfreader.database.DocumentRepository
import com.buildbrothers.wisepdfreader.model.Document
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @ViewModelInject constructor(
    private val documentRepository: DocumentRepository) : ViewModel() {
    val recentDocuments: LiveData<List<Document>> = documentRepository.recentDocuments


    fun insert(document: Document) = viewModelScope.launch(Dispatchers.IO) {
        documentRepository.insert(document)
    }
}