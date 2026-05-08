package com.example.topsports.ui.profile

import androidx.lifecycle.*
import com.example.topsports.data.local.entity.Athlete
import com.example.topsports.data.repository.SportsRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: SportsRepository) : ViewModel() {

    private val _athlete = MutableLiveData<Athlete?>()
    val athlete: LiveData<Athlete?> = _athlete

    fun loadAthleteByUsername(username: String) {
        viewModelScope.launch {
            _athlete.value = repository.getAthleteByUsername(username)
        }
    }

    fun updateAthlete(athlete: Athlete) {
        viewModelScope.launch {
            repository.updateAthlete(athlete)
            _athlete.value = athlete
        }
    }

    fun deleteAthlete(athlete: Athlete) {
        viewModelScope.launch {
            repository.deleteAthlete(athlete)
        }
    }

    class Factory(private val repository: SportsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProfileViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
