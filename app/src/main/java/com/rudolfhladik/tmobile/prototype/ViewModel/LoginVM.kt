package com.rudolfhladik.tmobile.prototype.ViewModel

import android.content.Context
import com.google.gson.JsonObject
import com.rudolfhladik.tmobile.prototype.networking.ILogin
import com.rudolfhladik.tmobile.prototype.networking.LoginAdapterFactory
import retrofit2.Response
import rx.Observable

/**
 *
 * Created by rd on 03/12/2016.
 */
class LoginVM() {

    fun getToken(context: Context): Observable<Response<JsonObject>> {
        val apiLoginAdapter = LoginAdapterFactory.getAdapter(context).create(ILogin::class.java)
        return apiLoginAdapter.getToken()
    }
}

