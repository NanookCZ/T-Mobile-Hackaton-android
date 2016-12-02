package com.rudolfhladik.tmobile.prototype.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.singletons.PreferencesSingleton

/**
 *
 * Created by rd on 13/10/16.
 */
class ActiveShipmentFragment : Fragment() {

    companion object {
        fun newInstance(): ActiveShipmentFragment {
            return ActiveShipmentFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_active_shipment)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        PreferencesSingleton.getInstance(context.applicationContext).setToken("muj TOKEN")

    }

    fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
    }
}
