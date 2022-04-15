package com.example.weatherapp.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceStorage @Inject constructor(
    private val context: Context
) {
    companion object {
        private const val CITY_ID = "city_id"
    }

    private val cityId = stringPreferencesKey(CITY_ID)

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    suspend fun updateCityId(cityId: String) {
        context.dataStore.edit { settings ->
            settings[this.cityId] = cityId
        }
    }

    fun getCityId(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[this.cityId] ?: ""
        }
    }
}