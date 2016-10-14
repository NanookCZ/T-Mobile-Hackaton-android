package com.dhl.demp.mytrack.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 *
 * Created by rd on 14/10/16.
 */
class LoginReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val greetings = intent?.getStringExtra("token")
        Log.i("Greatings: ", greetings)

    }
}
