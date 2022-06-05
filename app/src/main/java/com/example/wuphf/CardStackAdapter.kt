package com.example.wuphf

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class CardStackAdapter(
    private var spots: List<String> = emptyList()


) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {
    val pos1 : MutableLiveData<Int>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_spot, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos2 = spots[position]
//        holder.name.text = "${spot.id}. ${spot.name}"
//        holder.city.text = spot.city
//        Picasso.get().load("https://dog.ceo/api/breeds/image/random").into(holder.image)
//        Picasso.get().load("https://source.unsplash.com/THozNzxEP3g/600x800").into(holder.image)
//        val pic  = Picasso.get().load(spots.get).toString()


        Glide.with(holder.image)
            .load(spots.get(position))
            .into(holder.image)

        holder.image.layoutParams.height = 1300
        holder.image.scaleType = ImageView.ScaleType.FIT_XY

        holder.itemView.setOnClickListener { v ->
//            Toast.makeText(v.context, pic, Toast.LENGTH_SHORT).show()
//            Toast.makeText(v.context, spot.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return spots.size
    }

    fun setSpots(spots: List<String>) {
        this.spots = spots
    }

    fun getSpots(): List<String> {
        return spots
    }




    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.item_image)
//        var pos : SwipingFragment? = null
    }

}