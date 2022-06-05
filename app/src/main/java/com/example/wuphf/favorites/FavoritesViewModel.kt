package com.example.wuphf.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {
    var favoritesList: MutableLiveData<MutableList<FavoriteItem>> = MutableLiveData()

    fun add(item: FavoriteItem) {
        favoritesList.value?.add(item)
    }
}