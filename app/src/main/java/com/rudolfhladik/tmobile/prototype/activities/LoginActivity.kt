package com.rudolfhladik.tmobile.prototype.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.JsonObject
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.ViewModel.LoginVM
import com.rudolfhladik.tmobile.prototype.singletons.PreferencesSingleton
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Response
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *
 * Created by rd on 03/12/2016.
 */
class LoginActivity : AppCompatActivity() {

    lateinit var mSubscription: Subscription
    lateinit var mObserver: Observer<Response<JsonObject>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (PreferencesSingleton.getInstance(this).getToken() != "") {
//            startMain()

        }

        mLoginBtn.setOnClickListener {
            val dur: Long = 300
            val x = ObjectAnimator.ofFloat(mLoginBtn, "scaleX", 0.6f, 1.2f, 0.8f, 1f)
            x.duration = dur
            val y = ObjectAnimator.ofFloat(mLoginBtn, "scaleY", 0.6f, 1.2f, 0.8f, 1f)
            y.duration = dur
            val clickAnimSet = AnimatorSet()
            clickAnimSet.playTogether(x, y)
            clickAnimSet.start()

            login()
        }

        mObserver = object : Observer<Response<JsonObject>> {
            override fun onError(e: Throwable?) {
                loginFailed()
            }

            override fun onCompleted() {
            }

            override fun onNext(response: Response<JsonObject>) {
                handleResponse(response)
            }


        }

    }

    private fun loginFailed() {
        Snackbar.make(mActivityLogin, "login failed", Snackbar.LENGTH_LONG).show()
    }

    private fun login() {
        mSubscription = LoginVM()
                .getToken(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(mObserver)

    }

    private fun handleResponse(response: Response<JsonObject>?) {
        Log.i("loginClicked", " ")

        if (response != null) {
            if (response.isSuccessful) {
                val token = response.body().get("access_token").asString
                val refreshToken = response.body().get("refresh_token").asString
                PreferencesSingleton.getInstance(this).setToken(token)
                PreferencesSingleton.getInstance(this).setRefreshToken(refreshToken)

                Log.i("tokens", "token: $token , refreshToken: $refreshToken")
                startMain()
            } else {
                Log.i("loginFailed", "${response.code()} ")
                loginFailed()

            }
        }
    }

    private fun startMain() {
        startActivity(Intent(this, SelectCarActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
//        mSubscription?.unsubscribe()
    }
}
