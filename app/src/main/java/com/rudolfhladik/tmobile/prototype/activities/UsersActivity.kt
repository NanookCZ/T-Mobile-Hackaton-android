package com.rudolfhladik.tmobile.prototype.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.singletons.PreferencesSingleton
import kotlinx.android.synthetic.main.activity_users.*

/**
 *
 * Created by rd on 04/12/2016.
 */
class UsersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        setSupportActionBar(mToolbarUsers)
        mToolbarUsers.setNavigationIcon(R.drawable.ic_back)
        mToolbarUsers.setNavigationOnClickListener { onBackPressed() }

        val url = PreferencesSingleton.getInstance(this).getCarImgUrl()
        Glide.with(this).load(url).into(mCarImage)

    }
}
