package com.rudolfhladik.tmobile.prototype.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.adapters.RecNaviAdapter
import com.rudolfhladik.tmobile.prototype.model.NavItem
import com.rudolfhladik.tmobile.prototype.singletons.PreferencesSingleton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_toolbar.*
import java.util.*


/**
 *
 * Created by rd on 13/10/16.
 */
class MainActivity : AppCompatActivity() {

    var naviAdapter = RecNaviAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup toolbar
        setSupportActionBar(toolbar_main)


        //setup navigation
        mRecNavi.adapter = naviAdapter
        mRecNavi.layoutManager = LinearLayoutManager(this)
        val naviItems = ArrayList<NavItem>()
        naviItems.add(NavItem(R.drawable.ic_garage, getString(R.string.my_garage), RecNaviAdapter.MY_GARAGE))
        naviItems.add(NavItem(R.drawable.ic_car_state, getString(R.string.car_state), RecNaviAdapter.CAR_STATE))
        naviItems.add(NavItem(R.drawable.ic_car_ride, getString(R.string.ride), RecNaviAdapter.RIDE))
        naviItems.add(NavItem(R.drawable.ic_users, getString(R.string.users), RecNaviAdapter.USERS))

        naviAdapter.addItems(naviItems)
//        viewpager.adapter = mAdapter

//        MojioClient client = new MojioClient();

        val log = PreferencesSingleton.getInstance(this).getToken()
        Log.i("login", "$log ")
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
