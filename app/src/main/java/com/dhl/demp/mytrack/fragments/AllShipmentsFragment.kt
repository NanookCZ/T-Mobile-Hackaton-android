package com.dhl.demp.mytrack.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhl.demp.mytrack.R
import com.dhl.demp.mytrack.singletons.PreferencesSingleton


import kotlinx.android.synthetic.main.fragment_all_shipments.*

/**
 *
 * Created by rd on 13/10/16.
 */
class AllShipmentsFragment : Fragment() {
    companion object {
        fun newInstance(): AllShipmentsFragment {
            return AllShipmentsFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_all_shipments)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val mSingl = PreferencesSingleton.getInstance(context.applicationContext)
        text.text = mSingl.getToken()
    }

    fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
    }
}