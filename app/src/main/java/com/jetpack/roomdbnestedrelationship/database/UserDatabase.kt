package com.jetpack.roomdbnestedrelationship.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jetpack.roomdbnestedrelationship.dao.UserDao
import com.jetpack.roomdbnestedrelationship.entity.Playlist
import com.jetpack.roomdbnestedrelationship.entity.PlaylistSongCrossRef
import com.jetpack.roomdbnestedrelationship.entity.Song
import com.jetpack.roomdbnestedrelationship.entity.User

@Database(entities = [User::class, Playlist::class, Song::class, PlaylistSongCrossRef::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            val userInstance = INSTANCE
            if (userInstance != null) {
                return userInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).allowMainThreadQueries().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}














