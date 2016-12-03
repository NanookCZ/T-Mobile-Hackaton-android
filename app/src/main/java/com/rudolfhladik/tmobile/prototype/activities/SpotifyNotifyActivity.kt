package com.rudolfhladik.tmobile.prototype.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.JsonObject
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.ViewModel.RestVM
import kotlinx.android.synthetic.main.activity_spot_noti.*
import retrofit2.Response
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 *
 * Created by rd on 03/12/2016.
 */
class SpotifyNotifyActivity : AppCompatActivity() {

    lateinit var mSubscription: Subscription
    lateinit var mObserver: Observer<Response<JsonObject>>
    var i: Int = 40
    var run: Boolean = true
    var safeDistance: Float = 100.0f
    var rising = true
    var spotifyPlayed = true
    var avgSpeedArray = ArrayList<Int>()

    val dummyCarSpeed = 55

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spot_noti)

        mObserver = object : Observer<Response<JsonObject>> {
            override fun onError(e: Throwable) {
                requestFailed()
                Log.e("err", "${e.message} ")

            }

            override fun onCompleted() {
                if (run) {
                    Log.i("mySpeed", "$i km/h")
                    getData()
                    if (rising && i in 40..65) {
                        i++
                    } else if (rising && i > 65) {
                        rising = false
                    } else if (!rising && i > 40) {
                        i--
                    } else {
                        rising = true
                    }
                    if (avgSpeedArray.size > 10) avgSpeedArray.clear()
                    avgSpeedArray.add(i)
                    Log.i("averageSpeed ", "size: ${avgSpeedArray.size}")
                }
            }

            override fun onNext(response: Response<JsonObject>) {
                handleResponse(response)
            }
        }
        getData()
    }

    private fun handleResponse(response: Response<JsonObject>?) {
        if (response != null) {
            if (response.isSuccessful) {
                var speed = response.body().get("Data").asJsonArray.get(0).asJsonObject.get("Speed").asJsonObject.get("Value").asInt
                if (i > dummyCarSpeed) {
                    showWarning()
                } else {
                    listenToMusic()
                }


            } else {
                Log.i("requestFailed", "${response.code()} ")
                requestFailed()

            }
        }
    }

    private fun listenToMusic() {
        if (!spotifyPlayed) {
            mNotiIcon.setImageDrawable(getDrawable(R.drawable.spotify))
            mNotiText.text = " From your playlist.."
            mNotiSubText.text = " Queen - We are the champions //  Queen Greatest Hits - The Platinum Collection "
            spotifyPlayed = true

//                val leftAnim =
//                ViewGroup parent = (ViewGroup)target.getParent();
//                int distance = parent.getWidth() - target.getLeft();
//                getAnimatorAgent().playTogether(
//                        ObjectAnimator.ofFloat(target, "alpha", 0, 1),
//                        ObjectAnimator.ofFloat(target,"translationX",-distance,0)

        }
    }

    private fun showWarning() {
        if (spotifyPlayed) {
            mNotiIcon.setImageDrawable(getDrawable(R.drawable.stop))
            spotifyPlayed = false

        }


    }

    private fun getData() {
        mSubscription = RestVM()
                .getCars(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(mObserver)


    }

    private fun requestFailed() {
        Snackbar.make(mSpotify, "response from server failed", Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        run = false
    }
}
