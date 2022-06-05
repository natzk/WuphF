package com.example.wuphf.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wuphf.databinding.FragmentFavoritesBinding
import ui.MainActivity

//@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel : FavoritesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecycler()
        initFavoriteList()
    }

    private fun initViewModel() {
        viewModel.favoritesList.observe(requireActivity()) {
            binding.recycler.adapter?.notifyDataSetChanged()
        }
    }

    private fun initRecycler() {
        val list = viewModel.favoritesList.value as List<FavoriteItem>
        binding.recycler.layoutManager = GridLayoutManager(requireActivity(), 3)
        binding.recycler.adapter = FavoritesAdapter(list, object : FavoritesAdapter.FavoriteListener {
            override fun onFavoriteItemClicked(index: Int) {
                viewModel.select(index)
                (activity as MainActivity).openFragment(DogInfoFragment(), "Opening DogInfoFragment")
            }
            override fun onFavoriteItemLongClicked(index: Int) {
            }
        })
    }

    private fun initFavoriteList() {
        addTestFavoriteDog()
    }

    private fun addTestFavoriteDog() {
        viewModel.add(FavoriteItem("test", "test", null))
    }
}