package com.slailati.android.spectacle.ui.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.slailati.android.spectacle.data.datasource.*
import com.slailati.android.spectacle.data.repository.AuthRepository
import com.slailati.android.spectacle.data.repository.AuthRepositoryImpl
import com.slailati.android.spectacle.data.repository.MusicRepository
import com.slailati.android.spectacle.data.repository.MusicRepositoryImpl
import com.slailati.android.spectacle.domain.database.MainDatabase
import com.slailati.android.spectacle.domain.database.MyMusicsPlaylistDao
import com.slailati.android.spectacle.domain.service.DeezerService
import com.slailati.android.spectacle.ui.viewmodel.MyMusicPlaylistViewModel
import com.slailati.android.spectacle.ui.viewmodel.UserViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
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
//    single<LocalAuthRepository> { LocalAuthRepositoryImpl(get()) }
}

val dataSourceModule = module {
    single<FirebaseAuthDataSource> { FirebaseAuthDataSourceImpl(get()) }
    single<DeezerMusicDataSource> { DeezerMusicDataSourceImpl(get()) }
    single<MyMusicsPlaylistDataSource> { MyMusicsPlaylistDataSourceImpl(get()) }
//    single<LocalAuthDataSource> { LocalAuthDataSourceImpl(androidApplication()) }
}

val providerModule = module {
//    single<ThreadContextProvider> { ThreadContextProvider() }
}

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
}

val useCaseModule = module {
//    factory<FirebaseAuthUseCase> { (scope: CoroutineScope) -> FirebaseAuthUseCase(get(), scope) }
}

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { MyMusicPlaylistViewModel(get()) }
//    viewModel<HomeViewModel> { HomeViewModel() }
//    viewModel<WelcomeViewModel> { WelcomeViewModel(get(), get()) }
}

val networkModule = module {
    factory { provideDeezerOkHttpClient() }
    factory { provideDeezerService(get()) }
    single { provideDeezerRetrofit(get()) }
}

fun provideDeezerRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideDeezerOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun provideDeezerService(retrofit: Retrofit): DeezerService =
    retrofit.create(DeezerService::class.java)