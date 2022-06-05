package com.example.wuphf.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.wuphf.databinding.FragmentDogInfoBinding

class DogInfoFragment : Fragment() {

    private var _binding : FragmentDogInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel : FavoritesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRemoveButton()
    }

    private fun initRemoveButton() {
        binding.removeButton.setOnClickListener {
            viewModel.removeSelectedItem()
            activity?.onBackPressed()
        }
    }

}