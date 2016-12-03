package com.rudolfhladik.tmobile.prototype.networking

import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

/**
 *
 * Created by rd on 18/10/16.
 */

class RetrofitException internal constructor(message: String, url: String, response: Response<*>, kind: RetrofitException.Kind, exception: Throwable, retrofit: Retrofit) : RuntimeException(message, exception) {


    /** Identifies the event kind which triggered a {@link RetrofitException}. */
    enum class Kind {
        /** An {@link IOException} occurred while communicating to the server. */
        NETWORK,
        /** A non-200 HTTP status code was received from the server. */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    /** The request URL which produced the error. */
    val url: String
    /** Response object containing status code, headers, body, etc. */
    val response: Response<*>
    /** The event kind which triggered this error. */
    val kind: Kind
    /** The Retrofit this request was executed on */
    val retrofit: Retrofit

    init {
        this.url = url
        this.response = response
        this.kind = kind
        this.retrofit = retrofit
    }

    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no
     * response.
     *
     * @throws IOException if unable to convert the body to the specified {@code type}.
     */
    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>): T {

        val converter = retrofit.responseBodyConverter<T>(type, arrayOfNulls<Annotation>(0))
        return converter.convert(response.errorBody())
    }

    companion object {
        fun httpError(url: String, response: Response<*>, retrofit: Retrofit): RetrofitException {
            val message: String = "${response.code()} ${response.message()}"
            return RetrofitException(message, url, response, Kind.HTTP, Throwable(response.message()), retrofit)
        }
//        fun networkError(exception: IOException):RetrofitException {
//            return RetrofitException(exception.message?: "", "",  Response , Kind.NETWORK, exception, Retrofit.Builder().build())
//        }
//        fun unexpectedError(exception:Throwable):RetrofitException {
//            return RetrofitException(exception.message?: "", "", Response.Builder().build() , Kind.UNEXPECTED, exception, Retrofit.Builder().build())
//        }
    }
}