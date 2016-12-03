package com.rudolfhladik.tmobile.prototype.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 * Created by rd on 03/12/2016.
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}
