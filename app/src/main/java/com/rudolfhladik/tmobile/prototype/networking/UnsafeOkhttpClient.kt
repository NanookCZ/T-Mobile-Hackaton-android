package com.rudolfhladik.tmobile.prototype.networking

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * UnsafeOKhttpclient
 * Created by rd on 17/10/16.
 */
class UnsafeOkHttpClient {
    internal var client: OkHttpClient? = null
    private val HTTP_CACHE_MAX_SIZE: Long = 10 * 1024 * 1024.toLong()


    fun getUnsafeOkHttpClient(context: Context): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val x509trustmngr = object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            }
            val trustAllCerts = arrayOf<TrustManager>(x509trustmngr)

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, x509trustmngr)
            builder.hostnameVerifier { hostname, session -> true }
            val baseDir = context.cacheDir
            if (baseDir != null) {
                val cacheDir = File(baseDir, "HttpResponseCache")
                builder.cache(Cache(cacheDir, HTTP_CACHE_MAX_SIZE))
            }

            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}