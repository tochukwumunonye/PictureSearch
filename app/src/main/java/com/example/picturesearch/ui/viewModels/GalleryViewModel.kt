package com.example.picturesearch.ui.viewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.picturesearch.data.UnsplashPhoto
import com.example.picturesearch.data.remote.UnsplashResponse
import com.example.picturesearch.repository.UnsplashRepository
import kotlinx.coroutines.launch


class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepository,
    @Assisted state: SavedStateHandle
): ViewModel() {



   companion object{
        private const val DEFAULT_QUERY = "cats"
        private const val CURRENT_QUERY = "current_query"
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

     val photos = currentQuery.switchMap {queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)

    }

    fun searchPhotos(query: String) {
        currentQuery.value = query


    }




}