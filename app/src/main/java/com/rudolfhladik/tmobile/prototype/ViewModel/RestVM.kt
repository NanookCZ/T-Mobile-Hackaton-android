package com.rudolfhladik.tmobile.prototype.ViewModel

import android.content.Context
import com.google.gson.JsonObject
import com.rudolfhladik.tmobile.prototype.networking.IRest
import com.rudolfhladik.tmobile.prototype.networking.RestAdapterFactory
import com.rudolfhladik.tmobile.prototype.singletons.PreferencesSingleton
import retrofit2.Response
import rx.Observable

/**
 *
 * Created by rd on 03/12/2016.
 */
class RestVM() {

    fun getCars(context: Context): Observable<Response<JsonObject>> {
        val apiRestAdapter = RestAdapterFactory.getAdapter(context).create(IRest::class.java)
        val token = PreferencesSingleton.getInstance(context).getToken()
        return apiRestAdapter.getVehicles("Bearer $token")
    }
}
