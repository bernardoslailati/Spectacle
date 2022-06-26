package com.slailati.android.spectacle.domain.database

import androidx.room.*

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