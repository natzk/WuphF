package com.example.wuphf.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {
    var favoritesList: MutableLiveData<MutableList<FavoriteItem>> = MutableLiveData()
    private var selectedItem: MutableLiveData<Int> = MutableLiveData()

    init {
        favoritesList.value = mutableListOf()
        selectedItem.value = 0
    }

    fun add(item: FavoriteItem) {
        favoritesList.value?.add(item)
    }

    fun select(index: Int) {
        selectedItem.value = index
    }

    fun removeSelectedItem() {
        favoritesList.value?.removeAt(selectedItem.value!!)
    }
}