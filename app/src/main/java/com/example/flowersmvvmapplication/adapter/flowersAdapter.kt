package com.example.flowersmvvmapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flowersmvvmapplication.R
import com.example.flowersmvvmapplication.databinding.CardRowBinding
import com.example.flowersmvvmapplication.model.Flower_Model
import com.example.flowersmvvmapplication.util.ItemClickListener
import java.util.*


class flowersAdapter(rowLayout: Int, context: Context): RecyclerView.Adapter<CakesViewHolder>(){
    var mItems: List<Flower_Model>? = emptyList()

        set(value) {
            field = value
            //updates the list every time it is set, and notifies observers when changed;
            //also invalidates every item in the recyclerview list
            notifyDataSetChanged()
        }

    //this "set" is a convenience method for vars, similar to a setter in java
    private var mContext: Context? = null
    private var mrowLayout: Int = 0

    init {
        mContext = context
        mrowLayout = rowLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakesViewHolder{
        val withDataBinding: CardRowBinding = DataBindingUtil.inflate(
            //takes a layoutInflater arg, which can be initiated using LayoutInflater class
            LayoutInflater.from(parent.context),
            //Int arg, meaning the id of the layout to be inflated
            CakesViewHolder.LAYOUT,
            //viewgroup arg
            parent,
            false
        )
        return CakesViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CakesViewHolder, position: Int) {
        holder.viewDatabinding.also {
            it.flower = mItems!![position]
        }

        holder.setClickListener(object : ItemClickListener{
                override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                    if (isLongClick) {
                        Toast.makeText(
                                mContext,
                                "#" + (position+1) + " - " + mItems!!.get(position).instructions + " (Long click)",
                                Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(mContext, "#" + (position+1) + " - " + mItems!!.get(position).instructions, Toast.LENGTH_SHORT).show();
                    }
                }

                override fun OnError(view: View?, message: String?) {
                    TODO("Not yet implemented")
                }

            })


    }


    override fun getItemCount(): Int {
        return mItems!!.size
    }

    private fun getItem(adapterPosition: Int): Any {
        return if (mItems == null) 0 else mItems!!.size
    }
}

class CakesViewHolder(val viewDatabinding: CardRowBinding): RecyclerView.ViewHolder(viewDatabinding.root), View.OnClickListener, View.OnLongClickListener {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.card_row

    }

    // Click listener implemented here
    init {
        viewDatabinding.root.setOnClickListener(this)
        viewDatabinding.root.setOnLongClickListener(this)
    }

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


