package com.hanny.doordashlite.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanny.doordashlite.R
import com.hanny.doordashlite.models.Restaurant
import com.squareup.picasso.Picasso


class LocalResListAdapter
    (private val values: MutableList<Restaurant>) : RecyclerView.Adapter<LocalResListAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    inner class ViewHolder(var layout: View) : RecyclerView.ViewHolder(layout) {
        // each data item is just a string in this case

        val restCoverImg: ImageView = layout.findViewById(R.id.restaurant_cover_img)
        val restTitle: TextView = layout.findViewById(R.id.restaurant_title)
        val restDescription: TextView = layout.findViewById(R.id.restaurant_descriptions)

        val restStatus: TextView = layout.findViewById(R.id.restaurant_status)
    }

    fun add(position: Int, item: Restaurant) {
        values.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val inflater = LayoutInflater.from(
            parent.context
        )
        val v = inflater.inflate(R.layout.resturant_item_view, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name = values[position].name
        holder.restTitle.text = name
        holder.restDescription.text = values[position].description

        holder.restStatus.text = values[position].status

        Picasso.get()
            .load(values[position].cover_img_url)
            .placeholder(R.drawable.doordash_placeholder)
            .into(holder.restCoverImg)
    }

    override fun getItemCount(): Int {
        return values.size
    }

}