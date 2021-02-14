package com.example.flowersmvvmapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowersmvvmapplication.R
import com.example.flowersmvvmapplication.model.flowerModel
import com.example.flowersmvvmapplication.util.ItemClickListener


class flowersAdapter(posts: List<flowerModel>, rowLayout: Int, context: Context): RecyclerView.Adapter<flowersAdapter.ViewHolder>(){
    private var mItems: List<flowerModel>? = null
    private var mContext: Context? = null
    private var mrowLayout: Int

    init {
        mContext = context
        mItems = posts
        mrowLayout = rowLayout
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener{
        var flowerName: TextView? = null
        var flowerCategory: TextView? = null
        var productID: TextView? = null
        var flowerImage: ImageView? = null

        init {
            flowerName = itemView.findViewById<View>(R.id.flowerName) as TextView
            productID = itemView.findViewById<View>(R.id.productID) as TextView
            flowerCategory = itemView.findViewById<View>(R.id.flowerCategory) as TextView
            flowerImage = itemView.findViewById<View>(R.id.img) as ImageView
            itemView.tag = itemView
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        // Click listener implemented here

        private var clickListener: ItemClickListener? = null

        fun setClickListener(itemClickListener: ItemClickListener?) {
            clickListener = itemClickListener
        }

        override fun onClick(v: View?) {
            clickListener!!.onClick(v, position, false)
        }

        override fun onLongClick(v: View?): Boolean {
            clickListener!!.onClick(v, position, true)
            return true
        }

    }

    private fun getItem(adapterPosition: Int): Any {
        return if (mItems == null) 0 else mItems!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): flowersAdapter.ViewHolder{
        val v: View = LayoutInflater.from(parent.context).inflate(mrowLayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: flowersAdapter.ViewHolder, position: Int) {
        val basePhotoURL = "https://services.hanselandpetal.com/photos/"
        val flower: flowerModel = mItems!![position]
        holder.flowerName!!.text = flower.getName()
        holder.flowerCategory!!.text = flower.getCategory()
        holder.productID!!.text = flower.getProductId().toString()
        Glide.with(mContext!!)
            .load(basePhotoURL + flower.getPhoto()!!)
            .into(holder.flowerImage!!)
        holder.setClickListener(object : ItemClickListener{

            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                if (isLongClick) {
                    Toast.makeText(
                            mContext,
                            "#" + (position+1) + " - " + flower!!.getInstructions()!! + " (Long click)",
                            Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(mContext, "#" + (position+1) + " - " + flower.getInstructions()!!, Toast.LENGTH_SHORT).show();
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return mItems!!.size
    }
}

