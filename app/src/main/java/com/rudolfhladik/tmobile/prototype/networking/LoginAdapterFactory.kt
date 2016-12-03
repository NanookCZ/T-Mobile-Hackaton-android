package com.rudolfhladik.tmobile.prototype.networking

import android.content.Context
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier

/**
 *
 * Created by rd on 03/12/2016.
 */
object LoginAdapterFactory {
    val baseUrl = "https://accounts.moj.io/oauth2/token"

    fun getAdapter(context: Context): Retrofit {

        return createAdapter(context)
    }

    private fun createAdapter(context: Context): Retrofit {

        val restAdapter = Retrofit.Builder()
                .client(UnsafeOkHttpClient().getUnsafeOkHttpClient(context))
//                .baseUrl(BASE_URL)
                .baseUrl(RestAdapterFactory.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
//                        .serializeNulls()
                        .create()))
                .build()
        return restAdapter
    }
}