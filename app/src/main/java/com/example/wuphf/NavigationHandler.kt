package com.example.wuphf

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class NavigationHandler(val context: Context) {

    private var currentFragment: Fragment? = null
    private var targetFragmentID: Int? = null

    fun getCurrentFragment(): Fragment? {
        var fragment: Fragment? = null
        fragment = (context as FragmentActivity).supportFragmentManager.findFragmentByTag("Opening the Swiping Fragment")


        return fragment
    }
}