package com.example.wuphf.ui.faqFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wuphf.R

class FaqsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_faqs, container, false)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            FaqsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}