package com.rudolfhladik.tmobile.prototype.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.ViewModel.RestVM
import com.rudolfhladik.tmobile.prototype.adapters.RecSelectCarAdapter
import com.rudolfhladik.tmobile.prototype.model.Vehicle
import com.rudolfhladik.tmobile.prototype.singletons.PreferencesSingleton
import kotlinx.android.synthetic.main.activity_select_car.*
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
class SelectCarActivity : AppCompatActivity() {

    var selectAdapter = RecSelectCarAdapter()
    val gson = Gson()

    lateinit var mSubscription: Subscription
    lateinit var mObserver: Observer<Response<JsonObject>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_car)

        //setup toolbar
        setSupportActionBar(mToolbarCarSelect)

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

        //setup navigation
        mCarSelectRec.adapter = selectAdapter
        mCarSelectRec.layoutManager = LinearLayoutManager(this)
//        mCarSelectRec.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val items = ArrayList<Vehicle>()
        val jsonMile = JsonObject()
        val jsMileEle = Gson().fromJson("{ 'Value' : 11134 }", JsonElement::class.java)
        jsonMile.add("Odometer", jsMileEle)
        val jsonImg = JsonObject()
        val jsonElem: JsonElement = Gson().fromJson("{ 'Src' : 'http://' }", JsonElement::class.java)
        jsonImg.add("Image", jsonElem)
//                Gson().fromJson("{ 'Image' : { 'Src' : 'http://www.smucler.cz/photo-ct-43885---.jpg' } }  ")
        items.add(Vehicle("Škoda Kodiaq", "999902336414886", jsonImg, "2016", jsonMile))
        items.add(Vehicle("Škoda Superb", "999902336414886", jsonImg, "2016", jsonMile))

        getData()
//        selectAdapter.addItems(items)


    }

    private fun handleResponse(response: Response<JsonObject>?) {
        if (response != null) {
            if (response.isSuccessful) {

                val data = response.body().get("Data").asJsonArray
                var vehicles = ArrayList<Vehicle>()
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
            Snackbar.make(mActivitySelectCar, "get vehicles failed", Snackbar.LENGTH_LONG).show()
        else Snackbar.make(mActivitySelectCar, message, Snackbar.LENGTH_LONG).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mSubscription != null) {
            if (!mSubscription.isUnsubscribed) {
                mSubscription.unsubscribe()
            }
        }
    }
    //    Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
//        @Override
//        protected void setResource(Bitmap resource) {
//            RoundedBitmapDrawable circularBitmapDrawable =
//            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//            circularBitmapDrawable.setCircular(true);
//            imageView.setImageDrawable(circularBitmapDrawable);
//        }
//    });
}
