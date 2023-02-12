package com.example.mvvmlearn.ui

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmlearn.R
import com.example.mvvmlearn.data.model.CountryModel
import com.example.mvvmlearn.util.SVGUtil


 class CountryListAdapter(context: Context, items: ArrayList<CountryModel>) :
    RecyclerView.Adapter<CountryListAdapter.ItemViewHolder>() {

    private val mContext = context
    private var mItems = items
    private var mListener: CountryClickListener? = null

    fun interface CountryClickListener {
        fun onClick(country: CountryModel)
    }

    fun setListener(listener: CountryClickListener) {
        mListener = listener
    }

    fun setItems(items: ArrayList<CountryModel>) {
        this.mItems = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_country, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mItems[position]
        holder.name.text = item.name
        if (item.flag != null) {
            SVGUtil.fetchSvg(mContext, item.flag!!, holder.flag)
        }

        holder.body.setOnClickListener {
            mListener?.onClick(item)
        }

        holder.setIsRecyclable(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var body: LinearLayout = itemView.findViewById(R.id.body)
        var name: TextView = itemView.findViewById(R.id.name)
        var flag: ImageView = itemView.findViewById(R.id.flag)
    }
}