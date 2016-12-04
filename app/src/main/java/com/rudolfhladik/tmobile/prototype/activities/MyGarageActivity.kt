package com.rudolfhladik.tmobile.prototype.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.ViewModel.RestVM
import com.rudolfhladik.tmobile.prototype.adapters.RecMyGarageHorizontalAdapter
import com.rudolfhladik.tmobile.prototype.model.Vehicle
import com.rudolfhladik.tmobile.prototype.singletons.PreferencesSingleton
import kotlinx.android.synthetic.main.activity_my_garage.*
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
class MyGarageActivity : AppCompatActivity() {

    var selectAdapter = RecMyGarageHorizontalAdapter()
    val gson = Gson()

    lateinit var mSubscription: Subscription
    lateinit var mObserver: Observer<Response<JsonObject>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_garage)

        setSupportActionBar(mToolbarGarage)
        mToolbarGarage.setNavigationIcon(R.drawable.ic_back)
        mToolbarGarage.setNavigationOnClickListener { onBackPressed() }

        mObserver = object : Observer<Response<JsonObject>> {
            override fun onError(e: Throwable?) {
                getVehiclesFailed()
            }

            override fun onCompleted() {
            }

            override fun onNext(response: Response<JsonObject>) {
                handleResponse(response)
            }
        }
        mCars.adapter = selectAdapter
        mCars.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

    }

    private fun handleResponse(response: Response<JsonObject>?) {
        if (response != null) {
            if (response.isSuccessful) {

                val data = response.body().get("Data").asJsonArray
                val vehicles = ArrayList<Vehicle>()
                if (data.size() > 0) {
                    data.forEach { vehicles.add(gson.fromJson(it, Vehicle::class.java)) }
                } else getVehiclesFailed("No vehicles available")
                Log.i("vehicles", "size: ${vehicles.size} , name: ${vehicles[0].vehicleName}")
                if (!vehicles.isEmpty()) {
                    selectAdapter.addItems(vehicles.toMutableList())
                    PreferencesSingleton.getInstance(this).setCarImgUrl(vehicles[0].carImage.get("Src").asString)
                }
            } else {
                Log.i("getVehiclesFailed", "${response.code()} ")
                getVehiclesFailed()

            }
        }
    }

    private fun getData() {
        mSubscription = RestVM()
                .getCars(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(mObserver)
    }

    private fun getVehiclesFailed(message: String = "") {
        if (message == "")
            Snackbar.make(mMyGarageActivity, "get vehicles failed", Snackbar.LENGTH_LONG).show()
        else Snackbar.make(mMyGarageActivity, message, Snackbar.LENGTH_LONG).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mSubscription != null) {
            if (!mSubscription.isUnsubscribed) {
                mSubscription.unsubscribe()
            }
        }
    }
}