package com.example.wuphf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return inflater.inflate(R.layout.fragment_favorites, container, false)
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
        // initContactList()
    }

    private fun initRecycler() {
        val recycler = requireView().findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = GridLayoutManager(requireActivity(), 3)
        recycler.adapter = FavoritesAdapter(favoriteList,object : FavoritesAdapter.FavoriteListener {
            override fun onContactClicked(index: Int) {
                // TODO: Implement this method
            }
            override fun onContactLongClicked(index: Int) {
            }
        })
    }
}