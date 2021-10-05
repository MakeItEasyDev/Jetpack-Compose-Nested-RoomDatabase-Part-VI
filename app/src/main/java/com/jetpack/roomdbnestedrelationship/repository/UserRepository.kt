package com.jetpack.roomdbnestedrelationship.repository

import com.jetpack.roomdbnestedrelationship.dao.UserDao
import com.jetpack.roomdbnestedrelationship.entity.*

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(item: List<User>) {
        userDao.insertUser(item)
    }

    suspend fun addPlaylist(item: List<Playlist>) {
        userDao.insertPlaylist(item)
    }

    suspend fun addSong(item: List<Song>) {
        userDao.insertSong(item)
    }

    suspend fun addPlaylistSongCrossRef(item: List<PlaylistSongCrossRef>) {
        userDao.insertPlaylistSongCrossRef(item)
    }

    fun getUsersWithPlaylistAndSongs(userId: Int): List<UserWithPlaylistAndSongs> {
        return userDao.getUserWithPlaylistAndSongs(userId)
    }
}





























