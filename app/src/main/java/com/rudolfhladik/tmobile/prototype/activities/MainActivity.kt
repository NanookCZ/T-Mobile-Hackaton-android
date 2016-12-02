package com.rudolfhladik.tmobile.prototype.activities

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.adapters.MainPagerAdapter
import com.rudolfhladik.tmobile.prototype.recievers.LoginReceiver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_toolbar.*


/**
 *
 * Created by rd on 13/10/16.
 */
class MainActivity : AppCompatActivity() {

    val loginReceiver = LoginReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup toolbar
        setSupportActionBar(toolbar_main)
        toolbar_main.title = "My Track test"
        //setup main viewpager
        val mAdapter = MainPagerAdapter(applicationContext, supportFragmentManager)
        viewpager.adapter = mAdapter

        registerReceiver(loginReceiver, IntentFilter("com.dhl.demp.mytrack.LOGIN_RESPONSE"))

        val intent: Intent = Intent("com.dhl.demp.mydmac.ACTION_LOGIN")
        intent.putExtra("pin", "1199")
        sendBroadcast(intent)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(loginReceiver)

    }
}
