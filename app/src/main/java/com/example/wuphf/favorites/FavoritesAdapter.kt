package com.example.wuphf.favorites

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wuphf.R
import com.example.wuphf.databinding.FavoriteItemLayoutBinding

class FavoritesAdapter(private val favorites:List<FavoriteItem>, private val listener : FavoriteListener) : RecyclerView.Adapter<FavoritesAdapter.ItemViewHolder>() {

    interface FavoriteListener  {
        fun onFavoriteItemClicked(index: Int)
        fun onFavoriteItemLongClicked(index: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(FavoriteItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(favorites[position])

    override fun getItemCount() = favorites.size

    inner class ItemViewHolder(private val binding: FavoriteItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener, View.OnLongClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onFavoriteItemClicked(adapterPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            listener.onFavoriteItemLongClicked(adapterPosition)
            return true
        }

        fun bind(favoriteItem: FavoriteItem) {
            if (favoriteItem.imageUri != null) binding.favoriteImage.setImageURI(Uri.parse(favoriteItem.imageUri))
            else binding.favoriteImage.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}