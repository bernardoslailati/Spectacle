package com.slailati.android.spectacle.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MusicEntity::class],
    version = 1,
    exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract val myMusicsPlaylistDao: MyMusicsPlaylistDao
}
