package com.example.topsports.ui.home

import androidx.lifecycle.*
import com.example.topsports.TopSportsApp
import com.example.topsports.data.remote.model.LeagueItem
import com.example.topsports.data.remote.model.SportItem
import com.example.topsports.data.repository.SportsRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SportsRepository) : ViewModel() {

    private val _sports = MutableLiveData<List<SportItem>>()
    val sports: LiveData<List<SportItem>> = _sports

    private val _leagues = MutableLiveData<List<LeagueItem>>()
    val leagues: LiveData<List<LeagueItem>> = _leagues

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadSports()
    }

    fun loadSports() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            val result = repository.getAllSports()
            if (result.isEmpty()) {
                _error.value = "Nu s-au putut încărca sporturile. Verificați conexiunea."
            } else {
                _sports.value = result
            }
            _isLoading.value = false
        }
    }

    fun loadLeaguesForSport(sport: String) {
        viewModelScope.launch {
            _leagues.value = repository.getLeaguesBySport(sport)
        }
    }

    class Factory(private val repository: SportsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
