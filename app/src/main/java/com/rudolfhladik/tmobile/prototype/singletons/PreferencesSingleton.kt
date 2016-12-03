package com.rudolfhladik.tmobile.prototype.singletons

import android.content.Context
import android.content.SharedPreferences

/**
 * Singleton used to access private shared preferences
 * Created by rd on 13/10/16.
 */
class PreferencesSingleton private constructor(context: Context) {
    private val mContext: Context
    private var mPreferences: SharedPreferences
    private val TAG = PreferencesSingleton::class.java.canonicalName
    private val SHARED_PREFERENCES_NAME = PreferencesSingleton::class.java.canonicalName + "_preferences"
    private val ACCESS_TOKEN = "access_token"
    private val REFRESH_TOKEN = "refresh_token"
    private val SELECTED_CAR_NAME = "selected_car_name"

    init {
        mContext = context.applicationContext
        mPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        lateinit private var sInstance: PreferencesSingleton
        fun getInstance(context: Context): PreferencesSingleton {
            synchronized(PreferencesSingleton::class.java) {
                sInstance = PreferencesSingleton(context)
                return sInstance
            }
        }
    }

    fun getEditor(): SharedPreferences.Editor {
        return mPreferences.edit()
    }

    fun setToken(token: String): Unit {
        getEditor().putString(ACCESS_TOKEN, token)
                .commit()
    }

    fun getToken(): String {
        return mPreferences.getString(ACCESS_TOKEN, "")
    }

    fun setRefreshToken(token: String): Unit {
        getEditor().putString(REFRESH_TOKEN, token)
                .commit()
    }

    fun getRefreshToken(): String {
        return mPreferences.getString(REFRESH_TOKEN, "")
    }

    fun setSelectedCarName(vehicleName: String) {
        getEditor().putString(SELECTED_CAR_NAME, vehicleName)
                .commit()
    }

    fun getSelectedCarName(): String {
        return mPreferences.getString(SELECTED_CAR_NAME, "")
    }
}
