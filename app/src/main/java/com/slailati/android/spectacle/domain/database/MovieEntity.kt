package com.slailati.android.spectacle.domain.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_movies")
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var userId: String = "",
    var remoteId: Int,
    var genreId: Int,
    var title: String,
    var voteAverage: Float,
    var posterPath: String
)
