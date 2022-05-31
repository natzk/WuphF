package com.example.wuphf

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.wuphf.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        initUI()
    }

    private fun initUI() {
        openFragment(SwipingFragment(), "Opening the SwipingFragment")
        initFavoritesButton()
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    private fun initFavoritesButton() {
        val favoritesButton = binding.favoritesButton
        favoritesButton.setOnClickListener {
            openFragment(FavoritesFragment(), "Opening the FavoritesFragment")
        }
    }
}