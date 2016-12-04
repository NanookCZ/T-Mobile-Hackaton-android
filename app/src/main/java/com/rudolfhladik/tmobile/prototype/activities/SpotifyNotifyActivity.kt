package com.rudolfhladik.tmobile.prototype.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
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
    var i: Int = 40 //40
    var run: Boolean = true
    var safeDistance: Float = 100.0f
    var rising = true
    var spotifyPlayed = true
    var avgSpeedArray = ArrayList<Int>()
    val playSet = AnimatorSet()
    val warningSet = AnimatorSet()


    val dummyCarSpeed = 55

    var layoutReady = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            startAnim()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spot_noti)

        avgSpeedArray.add(1)

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
        mNotiSubText.viewTreeObserver.addOnGlobalLayoutListener(layoutReady)


    }


    private fun handleResponse(response: Response<JsonObject>?) {
        if (response != null) {
            if (response.isSuccessful) {
                var speed = response.body().get("Data").asJsonArray.get(0).asJsonObject.get("Speed").asJsonObject.get("Value").asInt
                if (i > dummyCarSpeed) {
                    showWarning(i)
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
        if (warningSet.isRunning) warningSet.cancel()
        if (!spotifyPlayed) {
            startAnim()
        }

    }

    private fun startAnim() {
        mNotiSubText.viewTreeObserver.removeOnGlobalLayoutListener(layoutReady)
        mNotiIcon.setImageDrawable(getDrawable(R.drawable.ic_spotify))
        mNotiIcon.scaleX = 1f
        mNotiIcon.scaleY = 1f
        mNotiText.text = " From your playlist.."
        mNotiSubText.text = " Queen - We are the champions //  Queen Greatest Hits "
        spotifyPlayed = true

        val parent: ViewGroup = mNotiSubText.parent as ViewGroup
        val distance = parent.width - mNotiSubText.left
        val distanceHeight = parent.height - mLeftNote.height
        val distanceHeightR = parent.height - mRightNote.height
        val flyYouFool = ObjectAnimator.ofFloat(mNotiSubText, "translationX", distance.toFloat(), 0f, -distance.toFloat())
        val leftNote = ObjectAnimator.ofFloat(mLeftNote, "translationY", 0f, -distanceHeight.toFloat())
        val rightNote = ObjectAnimator.ofFloat(mRightNote, "translationY", 0f, -distanceHeightR.toFloat())
        val leftVisibility = ObjectAnimator.ofFloat(mLeftNote, "alpha", 1f, 0f)
        val rightVisibility = ObjectAnimator.ofFloat(mRightNote, "alpha", 1f, 0f)

        with(rightNote) {
            duration = 2000
            repeatCount = 30
            interpolator = TimeInterpolator { it }
            repeatMode = Animation.INFINITE
        }
        with(rightVisibility) {
            duration = 2000
            repeatCount = 30
            interpolator = DecelerateInterpolator()
            repeatMode = Animation.INFINITE
        }
        with(leftNote) {
            duration = 2300
            repeatCount = 30
            interpolator = TimeInterpolator { it }
            repeatMode = Animation.INFINITE
        }
        with(leftVisibility) {
            duration = 2300
            repeatCount = 30
            interpolator = DecelerateInterpolator()
            repeatMode = Animation.INFINITE
        }
        with(flyYouFool) {
            duration = 6000
            repeatCount = 30
            interpolator = TimeInterpolator { it }
            repeatMode = Animation.INFINITE
//                start()
        }
        val roundRound = ObjectAnimator.ofFloat(mNotiIcon, "rotation", 360f, 0f)
        with(roundRound) {
            duration = 4000
            repeatCount = 30
            interpolator = TimeInterpolator { it }
            repeatMode = Animation.INFINITE
//                start()
        }
        playSet.playTogether(flyYouFool, roundRound, leftNote, leftVisibility, rightNote, rightVisibility)
        playSet.start()


    }

    private fun showWarning(speed: Int) {
        if (playSet.isRunning) {
            playSet.cancel()
        }
        if (spotifyPlayed) {
            mNotiIcon.setImageDrawable(getDrawable(R.drawable.stop))
            mNotiIcon.rotation = 0f
            mLeftNote.alpha = 0f
            mRightNote.alpha = 0f
            mNotiText.text = "Warning, you are too close!!"
            spotifyPlayed = false
            val x = ObjectAnimator.ofFloat(mNotiIcon, "scaleX", 0.2f, 0.95f, 0.6f, 0.7f)
            val dur: Long = 800
            with(x) {
                duration = dur
            }
            val y = ObjectAnimator.ofFloat(mNotiIcon, "scaleY", 0.2f, 0.95f, 0.6f, 0.7f)
//            val y = ObjectAnimator.ofFloat(mNotiIcon, "scaleY", 0.3f, 1.05f, 0.9f, 1f)
            with(y) {
                duration = dur
            }
            warningSet.playTogether(x, y)
            warningSet.start()
        }
        var speedCount: Int = 1
        avgSpeedArray.forEach { speedCount += it }
        var avgSpeed = speedCount / avgSpeedArray.size
        mNotiSubText.text = "Please keep safe distance. \n$speed km/h \n$avgSpeed km/h avg "
        mNotiSubText.translationX = 0f

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
