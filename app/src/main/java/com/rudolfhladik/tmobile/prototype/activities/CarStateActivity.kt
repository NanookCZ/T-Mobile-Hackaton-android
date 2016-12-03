package com.rudolfhladik.tmobile.prototype.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rudolfhladik.tmobile.prototype.R
import kotlinx.android.synthetic.main.activity_car_state.*

/**
 *
 * Created by rd on 03/12/2016.
 */
class CarStateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_state)

        //toolbar setup
        setSupportActionBar(mToolbarState)


    }
}
