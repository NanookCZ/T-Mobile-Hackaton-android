package com.dhl.demp.mytrack.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dhl.demp.mytrack.R
import com.dhl.demp.mytrack.fragments.ActiveShipmentFragment
import com.dhl.demp.mytrack.fragments.AllShipmentsFragment
import com.dhl.demp.mytrack.fragments.FragmentTabInfo

/**
 *
 * Created by rd on 13/10/16.
 */

class MainPagerAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val TAG = MainPagerAdapter::class.java.canonicalName
    private var mContext: Context

    init {
        mContext = context
    }

    override fun getItem(position: Int): Fragment {

        return sData[position].cls.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.getString(sData[position].tabNameId)
    }

    override fun getCount(): Int {
        return sData.size
    }

    companion object {
        private val sData = arrayOf<FragmentTabInfo>(FragmentTabInfo(ActiveShipmentFragment::class.java, R.string.active),
                FragmentTabInfo(AllShipmentsFragment::class.java, R.string.all))
    }
}
