package com.jetpack.roomdbnestedrelationship.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.jetpack.roomdbnestedrelationship.database.UserDatabase
import com.jetpack.roomdbnestedrelationship.entity.*
import com.jetpack.roomdbnestedrelationship.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val _readAllData = MutableLiveData<List<UserWithPlaylistAndSongs>>()
    var readAllData:LiveData<List<UserWithPlaylistAndSongs>> = _readAllData
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
    }

    fun getUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _readAllData.postValue(repository.getUsersWithPlaylistAndSongs(userId))
        }
    }

    fun addUser(item: List<User>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(item)
        }
    }

    fun addPlaylist(item: List<Playlist>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlaylist(item)
        }
    }

    fun addSong(item: List<Song>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSong(item)
        }
    }

    fun addPlaylistSongCrossRef(item: List<PlaylistSongCrossRef>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlaylistSongCrossRef(item)
        }
    }
}

class UserViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(application) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}


















