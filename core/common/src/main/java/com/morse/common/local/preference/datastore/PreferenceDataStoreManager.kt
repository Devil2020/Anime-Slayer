package com.morse.common.local.preference.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

enum class ExistanceType {
    INT,
    BOOLEAN,
    STRING,
    LONG
}

class PreferenceDataStoreManager private constructor(val dataStorePreferences: DataStore<Preferences>) {

    companion object {
        val KEY = "PREFERENCE-DATA-STORE"
        private val LOCK = Any()

        @Volatile
        var preferenceDataStoreManager: PreferenceDataStoreManager? = null

        fun builder(context: Context) = preferenceDataStoreManager ?: synchronized(LOCK) {
            val dataStore = context?.createDataStore(KEY)
            PreferenceDataStoreManager(dataStore).apply {
                preferenceDataStoreManager = this
            }
        }

    }

    fun isExist(key: String, type: ExistanceType): Flow<Boolean> {
        return when (type) {
            ExistanceType.BOOLEAN -> {
                dataStorePreferences?.data?.catch { exception -> // 1
                    // dataStore.data throws an IOException if it can't read the data
                    if (exception is IOException) { // 2
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }.map { it.contains(booleanPreferencesKey(key)) }
            }
            ExistanceType.INT -> {
                dataStorePreferences?.data?.catch { exception -> // 1
                    // dataStore.data throws an IOException if it can't read the data
                    if (exception is IOException) { // 2
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }.map { it.contains(intPreferencesKey(key)) }
            }
            ExistanceType.LONG -> {
                dataStorePreferences?.data?.catch { exception -> // 1
                    // dataStore.data throws an IOException if it can't read the data
                    if (exception is IOException) { // 2
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }.map { it.contains(longPreferencesKey(key)) }
            }
            ExistanceType.STRING -> {
                dataStorePreferences?.data?.catch { exception -> // 1
                    // dataStore.data throws an IOException if it can't read the data
                    if (exception is IOException) { // 2
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }.map { it.contains(stringPreferencesKey(key)) }
            }
        }
    }

    suspend fun putBoolean(tag: String, value: Boolean) {
        withContext(Dispatchers.Main) {
            dataStorePreferences.edit {
                it.set(booleanPreferencesKey(tag), value)
            }

        }
    }

    fun getBoolean(tag: String, defValue: Boolean): Flow<Boolean> {
        return dataStorePreferences?.data?.catch { exception -> // 1
            // dataStore.data throws an IOException if it can't read the data
            if (exception is IOException) { // 2
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[booleanPreferencesKey(tag)] ?: defValue }

    }

    suspend fun putString(tag: String, str: String) {
        withContext(Dispatchers.Main) {
            dataStorePreferences.edit {
                it.set(stringPreferencesKey(tag), str)
            }

        }
    }

    fun getString(tag: String, defStr: String): Flow<String> {
        return dataStorePreferences?.data?.catch { exception -> // 1
            // dataStore.data throws an IOException if it can't read the data
            if (exception is IOException) { // 2
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[stringPreferencesKey(tag)] ?: defStr }

    }

    suspend fun putInt(tag: String, value: Int) {
        withContext(Dispatchers.Main) {
            dataStorePreferences.edit {
                it.set(intPreferencesKey(tag), value)
            }
        }

    }

    fun getInt(tag: String, defValue: Int): Flow<Int> {
        return dataStorePreferences?.data?.catch { exception -> // 1
            // dataStore.data throws an IOException if it can't read the data
            if (exception is IOException) { // 2
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[intPreferencesKey(tag)] ?: defValue }
    }

    suspend fun putLong(tag: String, value: Long) {
        withContext(Dispatchers.Main) {
            dataStorePreferences.edit {
                it.set(longPreferencesKey(tag), value)
            }
        }
    }

    fun getLong(tag: String, defValue: Long): Flow<Long> {
        return dataStorePreferences?.data?.catch { exception -> // 1
            // dataStore.data throws an IOException if it can't read the data
            if (exception is IOException) { // 2
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[longPreferencesKey(tag)] ?: defValue }
    }

    suspend fun remove(tag: String) {
        withContext(Dispatchers.Main) {
            dataStorePreferences.edit {
                it.remove(stringPreferencesKey(tag))
            }
        }
    }

    suspend fun clearPreferences(commit: Boolean = false) {
        withContext(Dispatchers.Main) {
            dataStorePreferences.edit {
                it.clear()
            }
        }
    }

}