package com.rudolfhladik.tmobile.prototype.adapters

import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.extensions.inflate
import com.rudolfhladik.tmobile.prototype.model.Owner
import kotlinx.android.synthetic.main.owner_item.view.*

/**
 *
 * Created by rd on 03/12/2016.
 */
class RecOwnerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var mList = mutableListOf<Owner>()


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as OwnerViewHolder
        holder.bind(mList[position] as Owner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OwnerViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    fun addItems(news: MutableList<Owner>?) {
        if (news != null) {
            mList.clear()
            mList.plusAssign(news)
            Log.i("owners list size: ", mList.size.toString())
            notifyDataSetChanged()
        } else {
            mList.clear()
        }

    }

    fun addItem(item: Owner) {
        mList.add(item)
        notifyDataSetChanged()

    }

    class OwnerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.owner_item)) {
        fun bind(item: Owner) = with(itemView) {


            val roundIV = object : BitmapImageViewTarget(mCircleOwnerImage) {
                override fun setResource(resource: Bitmap) {
                    val circBitmapDrawable = RoundedBitmapDrawableFactory.create(mCircleOwnerImage.context.resources, resource)
                    circBitmapDrawable.isCircular = true
                    mCircleOwnerImage.setImageDrawable(circBitmapDrawable)
                }
            }
            val url = item.imgUrl
            Glide.with(mCircleOwnerImage.context).load(url).asBitmap().centerCrop().into(roundIV)

            mOwnerName.text = item.userName
            mOwnerLocationAndType.text = "${item.origin}, ${item.type}"
            mLastDrive.text = item.lastRide


        }

//            val url = "http://kclrfanzone.com/wp-content/uploads/2016/04/3022879-inline-s-6-2013-fifa-world-cup-brasil-ball.jpg"
//            val roundIV = object : BitmapImageViewTarget(imageView) {
//                override fun setResource(resource: Bitmap) {
//                    val circBitmapDrawable = RoundedBitmapDrawableFactory.create(imageView.context.resources, resource)
//                    circBitmapDrawable.isCircular = true
//                    imageView.setImageDrawable(circBitmapDrawable)
//                }
//            }
//
//            Glide.with(this).load(url).asBitmap().centerCrop().into(roundIV)


    }

}
