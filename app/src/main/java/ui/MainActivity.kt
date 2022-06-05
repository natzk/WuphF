package ui


import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.wuphf.FaqsFragment
import com.example.wuphf.favorites.FavoritesFragment
import com.example.wuphf.R
import com.example.wuphf.SwipingFragment
import com.example.wuphf.databinding.ActivityMainBinding

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var drawer: DrawerLayout

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        initUI()
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        drawer = binding.drawerLayout

        val navigationView = binding.navView

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    openFragment(SwipingFragment(), "Opening the Swiping Fragment")
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.nav_favorites-> {
                    openFragment(FavoritesFragment(), "Opening the favorites Fragment")
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    true
                }

                R.id.nav_faqs-> {
                    openFragment(FaqsFragment(), "Opening the Faqs Fragment")
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    true
                }


                else -> {
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    false
                }
            }
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun initUI() {
        openFragment(SwipingFragment(), "Opening the Swiping Fragment")
//        initFavoritesButton()
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

//    private fun initFavoritesButton() {
//        val favoritesButton = binding.favoritesButton
//        favoritesButton.setOnClickListener {
//            openFragment(FavoritesFragment(), "Opening the FavoritesFragment")
//        }
//    }
}
