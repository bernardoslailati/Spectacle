package com.slailati.android.spectacle.data.local.database

import androidx.room.*
import com.slailati.android.spectacle.data.local.model.MusicEntity

@Dao
interface MyMusicsPlaylistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(music: MusicEntity): Long

    @Delete
    fun delete(music: MusicEntity)

    @Query("DELETE FROM my_musics_playlist WHERE id = :musicId")
    fun deleteById(musicId: Int)

    @Query("SELECT * FROM my_musics_playlist")
    fun getAll(): List<MusicEntity>

    @Query("SELECT * FROM my_musics_playlist WHERE userId = :userId")
    fun getAllByUserId(userId: String): List<MusicEntity>

}