package com.gaia.wanandroid.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.gaia.wanandroid.MainApplication
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

object DataStoreUtils {
    private val MainApplication.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
    private val dataStore = MainApplication.instance.dataStore

    /**
     * 存放数据
     */
    fun <T> putData(key: String, value: T) {
        runBlocking {
            when (value) {
                is String -> {
                    putString(key, value)
                }
                is Int -> {
                    putInt(key, value)
                }
                is Long -> {
                    putLong(key, value)
                }
                is Float -> {
                    putFloat(key, value)
                }
                is Double -> {
                    putDouble(key, value)
                }
                is Boolean -> {
                    putBoolean(key, value)
                }
                else -> throw IllegalArgumentException("This type cannot be saved to the Data Store")
            }
        }
    }

    /**
     * 取出数据
     */
    fun <T> getData(key: String, defaultValue: T): T {
        val data = when (defaultValue) {
            is String -> {
                getString(key, defaultValue)
            }
            is Int -> {
                getInt(key, defaultValue)
            }
            is Long -> {
                getLong(key, defaultValue)
            }
            is Float -> {
                getFloat(key, defaultValue)
            }
            is Double -> {
                getDouble(key, defaultValue)
            }
            is Boolean -> {
                getBoolean(key, defaultValue)
            }
            else -> {
                throw IllegalArgumentException("This type cannot be saved to the Data Store")
            }
        }
        return data as T
    }


    /**
     * 存放Int数据
     */
    private suspend fun putInt(key: String, value: Int) = dataStore.edit {
        it[intPreferencesKey(key)] = value
    }

    /**
     * 存放Long数据
     */
    private suspend fun putLong(key: String, value: Long) = dataStore.edit {
        it[longPreferencesKey(key)] = value
    }

    /**
     * 存放String数据
     */
    private suspend fun putString(key: String, value: String) = dataStore.edit {
        it[stringPreferencesKey(key)] = value
    }

    /**
     * 存放Boolean数据
     */
    private suspend fun putBoolean(key: String, value: Boolean) = dataStore.edit {
        it[booleanPreferencesKey(key)] = value
    }

    /**
     * 存放Float数据
     */
    private suspend fun putFloat(key: String, value: Float) = dataStore.edit {
        it[floatPreferencesKey(key)] = value
    }

    /**
     * 存放Double数据
     */
    private suspend fun putDouble(key: String, value: Double) = dataStore.edit {
        it[doublePreferencesKey(key)] = value
    }

    /**
     * 取出Int数据
     */
    private fun getInt(key: String, default: Int = 0): Int = runBlocking {
        return@runBlocking dataStore.data.map {
            it[intPreferencesKey(key)] ?: default
        }.first()
    }

    /**
     * 取出Long数据
     */
    private fun getLong(key: String, default: Long = 0): Long = runBlocking {
        return@runBlocking dataStore.data.map {
            it[longPreferencesKey(key)] ?: default
        }.first()
    }

    /**
     * 取出String数据
     */
    private fun getString(key: String, default: String? = null): String = runBlocking {
        return@runBlocking dataStore.data.map {
            it[stringPreferencesKey(key)] ?: default
        }.first()!!
    }

    /**
     * 取出Boolean数据
     */
    private fun getBoolean(key: String, default: Boolean = false): Boolean = runBlocking {
        return@runBlocking dataStore.data.map {
            it[booleanPreferencesKey(key)] ?: default
        }.first()
    }

    /**
     * 取出Float数据
     */
    private fun getFloat(key: String, default: Float = 0.0f): Float = runBlocking {
        return@runBlocking dataStore.data.map {
            it[floatPreferencesKey(key)] ?: default
        }.first()
    }

    /**
     * 取出Double数据
     */
    private fun getDouble(key: String, default: Double = 0.00): Double = runBlocking {
        return@runBlocking dataStore.data.map {
            it[doublePreferencesKey(key)] ?: default
        }.first()
    }

    /**
     * 清空数据
     */
    fun DataStore<Preferences>.clear() = runBlocking { edit { it.clear() } }

}