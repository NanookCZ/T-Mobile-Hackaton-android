package com.rudolfhladik.tmobile.prototype.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.rudolfhladik.tmobile.prototype.R
import com.rudolfhladik.tmobile.prototype.extensions.inflate
import com.rudolfhladik.tmobile.prototype.model.NavItem
import kotlinx.android.synthetic.main.navi_item.view.*

/**
 *
 * Created by rd on 03/12/2016.
 */
class RecMyGarageHorizontalAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var mList = mutableListOf<NavItem>()


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as MyGarageViewHolder
        holder.bind(mList[position] as NavItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyGarageViewHolder(parent)
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

    class MyGarageViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.my_garage_item)) {
        fun bind(item: NavItem) = with(itemView) {
            mIcon.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.iconRes))
            mText.text = item.text

            val url = "http://kclrfanzone.com/wp-content/uploads/2016/04/3022879-inline-s-6-2013-fifa-world-cup-brasil-ball.jpg"

//            val roundIV = object : BitmapImageViewTarget(imageView) {
//                override fun setResource(resource: Bitmap) {
//                    val circBitmapDrawable = RoundedBitmapDrawableFactory.create(imageView.context.resources, resource)
//                    circBitmapDrawable.isCircular = true
//                    imageView.setImageDrawable(circBitmapDrawable)
//                }
//            }
//            Glide.with(this).load(url).asBitmap().centerCrop().into(roundIV)


        }

    }
}
