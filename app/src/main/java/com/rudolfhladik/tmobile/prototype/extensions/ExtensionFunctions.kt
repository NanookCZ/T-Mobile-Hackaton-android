package com.rudolfhladik.tmobile.prototype.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Created by rd on 03/12/2016.
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun String?.formatDate(): String {
    if (this != null) {
        val sourceSdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
        val resultSdf = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
        try {
            val time = this.replace("T", " ")
            return resultSdf.format(sourceSdf.parse(time.replace("Z", "")))
        } catch (e: Exception) {
            return ""
        }
    } else return ""

}

fun String?.formatYear(): String {
    if (this != null) {
        val sourceSdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
        val resultSdf = SimpleDateFormat("yyyy", Locale.getDefault())
        try {
            val time = this.replace("T", " ")
            return resultSdf.format(sourceSdf.parse(time.replace("Z", "")))
        } catch (e: Exception) {
            return ""
        }
    } else return ""

}
