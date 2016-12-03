package com.rudolfhladik.tmobile.prototype.adapters

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.activities.CarStateActivity
import com.rudolfhladik.tmobile.prototype.activities.RideActivity
import com.rudolfhladik.tmobile.prototype.extensions.inflate
import com.rudolfhladik.tmobile.prototype.model.NavItem
import kotlinx.android.synthetic.main.navi_item.view.*

/**
 *
 * Created by rd on 03/12/2016.
 */


class RecNaviAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var mList = mutableListOf<NavItem>()

    companion object {
        @JvmStatic
        val MY_GARAGE: Int = 0
        @JvmStatic
        val CAR_STATE: Int = 1
        @JvmStatic
        val RIDE: Int = 2
        @JvmStatic
        val USERS: Int = 3
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as NaviViewHolder
        holder.bind(mList[position] as NavItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NaviViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    fun addItems(news: MutableList<NavItem>?) {
        if (news != null) {
            mList.clear()
            mList.plusAssign(news)
            Log.i("plus: ", mList.size.toString())
            notifyDataSetChanged()
        } else {
            mList.clear()
        }

    }

    fun addItem(item: NavItem) {
        mList.add(item)
        notifyDataSetChanged()

    }

    class NaviViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.navi_item)) {
        fun bind(item: NavItem) = with(itemView) {
            mIcon.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.iconRes))
            mText.text = item.text
            mIcon.setOnClickListener { navigate(item.position) }
            mText.setOnClickListener { navigate(item.position) }
            mItem.setOnClickListener { navigate(item.position) }


        }

        private fun navigate(position: Int) {
            when (position) {
                MY_GARAGE -> {
                    itemView.context.startActivity(Intent(itemView.context, RideActivity::class.java))
                }
                CAR_STATE -> {
                    itemView.context.startActivity(Intent(itemView.context, CarStateActivity::class.java))

                }

                RIDE -> {
                    itemView.context.startActivity(Intent(itemView.context, RideActivity::class.java))
                }
                USERS -> {
                    itemView.context.startActivity(Intent(itemView.context, RideActivity::class.java))
                }
            }
        }
    }
}

