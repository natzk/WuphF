package com.example.wuphf.ui.favoritesFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wuphf.data.DogRepository
import com.example.wuphf.data.local.model.Dog
import com.example.wuphf.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(val dogRepository: DogRepository) : ViewModel() {

    var favList: MutableLiveData<Resource<List<Dog>>> = MutableLiveData()
    var selectedDog: Int = 0
    lateinit var extractedList: List<Dog>

    fun getAllFavourites(){
        viewModelScope.launch {
            val response = dogRepository.getAllFavourites()

            if(response.isNotEmpty()){
                favList.postValue(Resource.Success(response))
                extractedList = Resource.Success(response).data!!
            }else{
                favList.postValue(Resource.Error("Some error occurred, try again"))

            }
        }
    }

    fun select(index: Int) {
        selectedDog = index
    }

    /*
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
    }*/
}