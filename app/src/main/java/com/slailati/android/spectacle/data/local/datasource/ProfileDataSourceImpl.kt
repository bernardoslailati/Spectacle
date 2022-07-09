package com.slailati.android.spectacle.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.slailati.android.spectacle.data.remote.model.Profile
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class ProfileDataSourceImpl(
    private val context: Context,
) : ProfileDataSource {

    companion object {
        val USER_PROFILE_PREFERENCES_KEY = stringPreferencesKey("userProfile")
    }

    private fun checkIsLogged(): Boolean = runBlocking {
        context.dataStore.data.map { preferences ->
            try {
                preferences[USER_PROFILE_PREFERENCES_KEY]?.isNotEmpty() ?: false
            } catch (e: Exception) {
                false
            }
        }.first()
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override suspend fun insertProfile(profile: Profile) {
        context.dataStore.edit { settings ->
            settings[USER_PROFILE_PREFERENCES_KEY] = profile.uuid
        }
    }

    override suspend fun isLogged(): Boolean {
        return checkIsLogged()
    }

    override suspend fun logout(): Boolean {
        context.dataStore.edit { settings ->
            settings.remove(USER_PROFILE_PREFERENCES_KEY)
        }
        return true
    }

}