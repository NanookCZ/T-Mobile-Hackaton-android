package com.rudolfhladik.tmobile.prototype.adapters

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.activities.MainActivity
import com.rudolfhladik.tmobile.prototype.extensions.formatYear
import com.rudolfhladik.tmobile.prototype.extensions.inflate
import com.rudolfhladik.tmobile.prototype.model.Owner
import com.rudolfhladik.tmobile.prototype.model.Vehicle
import com.rudolfhladik.tmobile.prototype.singletons.PreferencesSingleton
import kotlinx.android.synthetic.main.my_garage_item.view.*
import java.util.*

/**
 *
 * Created by rd on 03/12/2016.
 */
class RecMyGarageHorizontalAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var mList = mutableListOf<Vehicle>()


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as MyGarageViewHolder
        holder.bind(mList[position] as Vehicle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyGarageViewHolder(parent)
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

    class MyGarageViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.my_garage_item)) {
        val recAdapter = RecOwnerAdapter()

        fun bind(item: Vehicle) = with(itemView) {

            Glide.with(itemView.context).load(item.carImage.get("Src").asString).into(mCarImage)
            mSpeed.text = item.year.formatYear()
            mFuel.text = item.mileage.get("Value").asString ?: ""
            mError.text = "2"
            mCardInfo.setOnClickListener {
                PreferencesSingleton.getInstance(itemView.context).setSelectedCarName(item.vehicleName)
                Toast.makeText(itemView.context, "${item.vehicleName} selected, good choice.", Toast.LENGTH_LONG).show()
                itemView.context.startActivity(Intent(this.context, MainActivity::class.java))
            }

            mUsersList.adapter = recAdapter
            mUsersList.layoutManager = LinearLayoutManager(mUsersList.context)
            val listUsers = ArrayList<Owner>()
            listUsers.add(Owner("Svetlana Margetova", "4.12.2016", "https://files.slack.com/files-pri/T308GDCRG-F3ADT5SHK/sveta.jpg", "owner", "Prague"))
            listUsers.add(Owner("Rudolf Hladik", "4.12.2016", "https://trello-attachments.s3.amazonaws.com/52589bca65b0b14e1d004d94/581efa2dafc1d1f2ca807a35/45b27f2a525bd8cd3c4921ecbfa1c321/ruda.jpg", "co-owner", "Prague"))
            recAdapter.addItems(listUsers.toMutableList())

        }

    }
}
