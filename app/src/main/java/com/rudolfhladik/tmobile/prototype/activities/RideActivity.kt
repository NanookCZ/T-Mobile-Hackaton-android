package com.rudolfhladik.tmobile.prototype.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.rudolfhladik.tmobile.prototype.R
import kotlinx.android.synthetic.main.activity_ride.*

/**
 *
 * Created by rd on 03/12/2016.
 */
class RideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride)

        //toolbar setup
        setSupportActionBar(mToolbarRide)
        mToolbarRide.setNavigationIcon(R.drawable.ic_back)
        mToolbarRide.setNavigationOnClickListener { onBackPressed() }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.ride_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mCheckDistance) {
            startActivity(Intent(this, SpotifyNotifyActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }
}
