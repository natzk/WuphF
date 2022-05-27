package com.example.wuphf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wuphf.databinding.FragmentFavoritesBinding

// TODO: Adapt to FavoritesAdapter

class FavoritesFragment : Fragment() {

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private var favoriteList: MutableList<FavoriteItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        // initContactList()
    }

    private fun initRecycler() {
        binding.recycler.layoutManager = GridLayoutManager(requireContext(),3 )
        binding.recycler.adapter = FavoritesAdapter(favoriteList,object : FavoritesAdapter.FavoriteListener {
            override fun onContactClicked(index: Int) {
                // TODO: Implement this method
            }
            override fun onContactLongClicked(index: Int) {
            }
        })
    }
}