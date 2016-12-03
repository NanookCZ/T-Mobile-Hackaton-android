package com.rudolfhladik.tmobile.prototype.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.adapters.RecSelectCarAdapter
import com.rudolfhladik.tmobile.prototype.model.Vehicle
import kotlinx.android.synthetic.main.activity_select_car.*
import java.util.*

/**
 *
 * Created by rd on 03/12/2016.
 */
class SelectCarActivity : AppCompatActivity() {

    var selectAdapter = RecSelectCarAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_car)

        //setup toolbar
//        setSupportActionBar(toolbar_main)


        //setup navigation
        mCarSelectRec.adapter = selectAdapter
        mCarSelectRec.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

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

        selectAdapter.addItems(items)


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
