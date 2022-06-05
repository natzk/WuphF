package com.example.wuphf.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wuphf.databinding.FragmentFavoritesBinding

// TODO: Adapt to FavoritesAdapter

//@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private var favoriteListLiveData = MutableLiveData<MutableList<FavoriteItem>>()
    private var favoriteList: MutableList<FavoriteItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initFavoriteList()
    }

    private fun initRecycler() {
        val recycler = binding.recycler
        recycler.layoutManager = GridLayoutManager(requireActivity(), 3)
        recycler.adapter = FavoritesAdapter(favoriteList,object : FavoritesAdapter.FavoriteListener {
            override fun onFavoriteItemClicked(index: Int) {
                addTestFavoriteDog()
                binding.recycler.adapter?.notifyDataSetChanged()
            }
            override fun onFavoriteItemLongClicked(index: Int) {
            }
        })
    }

    private fun initFavoriteList() {
        favoriteListLiveData.value = favoriteList
        addTestFavoriteDog()
    }

    private fun addTestFavoriteDog() {
        favoriteList.add(FavoriteItem("test", "test", null))
    }
}