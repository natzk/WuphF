package com.example.wuphf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wuphf.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

// TODO: Adapt to FavoritesAdapter

//@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private var favoriteList: MutableList<FavoriteItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {

                }
            }
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
            override fun onContactClicked(index: Int) {
                Toast.makeText(requireContext(), "Not implemented yet!", Toast.LENGTH_SHORT).show()
            }
            override fun onContactLongClicked(index: Int) {
            }
        })
    }

    private fun initFavoriteList() {
        val favTest = FavoriteItem("test", "test", null)
        favoriteList.add(favTest)
        favoriteList.add(favTest)
        favoriteList.add(favTest)
        favoriteList.add(favTest)
        favoriteList.add(favTest)
        favoriteList.add(favTest)
        favoriteList.add(favTest)
        favoriteList.add(favTest)
        binding.recycler.adapter?.notifyDataSetChanged()
    }
}