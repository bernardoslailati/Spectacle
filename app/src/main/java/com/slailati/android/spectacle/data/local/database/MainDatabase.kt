package com.slailati.android.spectacle.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.slailati.android.spectacle.data.local.model.MovieEntity
import com.slailati.android.spectacle.data.local.model.MusicEntity

@Database(entities = [MusicEntity::class, MovieEntity::class],
    version = 1,
    exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract val myMusicsPlaylistDao: MyMusicsPlaylistDao
    abstract val myMoviesDao: MyMoviesDao
}
