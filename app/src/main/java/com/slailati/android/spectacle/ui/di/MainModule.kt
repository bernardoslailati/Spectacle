package com.slailati.android.spectacle.ui.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.slailati.android.spectacle.data.datasource.*
import com.slailati.android.spectacle.data.repository.*
import com.slailati.android.spectacle.domain.database.MainDatabase
import com.slailati.android.spectacle.domain.database.MyMusicsPlaylistDao
import com.slailati.android.spectacle.domain.service.DeezerService
import com.slailati.android.spectacle.domain.service.TheMovieDatabaseService
import com.slailati.android.spectacle.ui.viewmodel.MovieViewModel
import com.slailati.android.spectacle.ui.viewmodel.MusicViewModel
import com.slailati.android.spectacle.ui.viewmodel.UserViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideMyMusicsPlaylistDao(get()) }
}

fun provideDatabase(application: Application): MainDatabase {
    return Room.databaseBuilder(application, MainDatabase::class.java, "spectacle_database")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideMyMusicsPlaylistDao(database: MainDatabase): MyMusicsPlaylistDao {
    return database.myMusicsPlaylistDao
}

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<MusicRepository> { MusicRepositoryImpl(get(), get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}

val dataSourceModule = module {
    single<FirebaseAuthDataSource> { FirebaseAuthDataSourceImpl(get()) }
    single<DeezerMusicDataSource> { DeezerMusicDataSourceImpl(get()) }
    single<MyMusicsPlaylistDataSource> { MyMusicsPlaylistDataSourceImpl(get()) }
    single<TheMovieDatabaseDataSource> { TheMovieDatabaseDataSourceImpl(get()) }
    single<MyMoviesDataSource> { MyMoviesDataSourceImpl(get()) }
    single<ProfileDataSource> { ProfileDataSourceImpl(androidContext()) }
}

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
}

val viewModelModule = module {
    viewModel { UserViewModel(get(), get()) }
    viewModel { MusicViewModel(get()) }
    viewModel { MovieViewModel(get()) }
}

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideDeezerService(get()) }
    factory { provideTheMovieDatabaseService(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun provideDeezerService(okHttpClient: OkHttpClient): DeezerService =
    Retrofit.Builder()
        .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(DeezerService::class.java)

fun provideTheMovieDatabaseService(okHttpClient: OkHttpClient): TheMovieDatabaseService =
    Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TheMovieDatabaseService::class.java)