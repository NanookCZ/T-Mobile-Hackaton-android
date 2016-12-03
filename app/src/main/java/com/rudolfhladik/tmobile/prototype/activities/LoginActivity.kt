package com.rudolfhladik.tmobile.prototype.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rudolfhladik.tmobile.prototype.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 *
 * Created by rd on 03/12/2016.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginBtn.setOnClickListener { login() }

    }

    private fun login() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
