package com.example.francsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.francsapp.fragments.HomeFragment
import com.example.francsapp.fragments.FavoritesFragment
import com.example.francsapp.fragments.CartFragment
import com.example.francsapp.fragments.HistoryFragment
import com.example.francsapp.fragments.UserDataFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var flFragment: FrameLayout
    lateinit var bottomNavView: BottomNavigationView

    var homeFragment = HomeFragment()
    var historyFragment = HistoryFragment()
    var userFragment = UserDataFragment()
    var favoritesFragment = FavoritesFragment()
    var cartFragment = CartFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flFragment = findViewById<FrameLayout>(R.id.fragmentContainerView2)

        setCurrentFragment(homeFragment)
        bottomNavView = findViewById(R.id.bottom_bar) as BottomNavigationView

        bottomNavView.setOnItemSelectedListener {
            fragmentRedirect(it.itemId)
        }

    }

    private fun fragmentRedirect(itemId: Int): Boolean {
        when (itemId) {
            R.id.homeFragment -> setCurrentFragment(homeFragment)
            R.id.historyFragment -> setCurrentFragment(historyFragment)
            R.id.userDataFragment -> setCurrentFragment(userFragment)
            R.id.favoritesFragment -> setCurrentFragment(
                favoritesFragment
            )
            R.id.cartFragment -> setCurrentFragment(cartFragment)
        }
        return true
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView2, fragment)
            commit()
        }
    }

    override fun onBackPressed() {
        bottomNavView.setSelectedItemId(R.id.homeFragment)
    }
}