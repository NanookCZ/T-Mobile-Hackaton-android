package com.rudolfhladik.tmobile.prototype.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.extensions.inflate
import com.rudolfhladik.tmobile.prototype.model.Vehicle
import kotlinx.android.synthetic.main.car_select_item.view.*

/**
 *
 * Created by rd on 03/12/2016.
 */


class RecSelectCarAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var mList = mutableListOf<Vehicle>()


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as SelectCarViewHolder
        holder.bind(mList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SelectCarViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    fun addItems(news: MutableList<Vehicle>?) {
        if (news != null) {
            mList.clear()
            mList.plusAssign(news)
            Log.i("plus: ", mList.size.toString())
            notifyDataSetChanged()
        } else {
            mList.clear()
        }

    }

    fun addItem(item: Vehicle) {
        mList.add(item)
        notifyDataSetChanged()

    }

    class SelectCarViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.car_select_item)) {
        fun bind(item: Vehicle) = with(itemView) {
//            mIcon.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.carImage.get("Src")))
            Glide.with(itemView.context).load(item.carImage.get("Src")).into(mCarImage)
            mYear.text = item.year
//            mMileage.text = item.mileage?.get("Value").toSt

        }

    }
}


