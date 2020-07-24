package com.siscofran.sehatq.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.siscofran.sehatq.R
import com.siscofran.sehatq.ui.login.LoginActivity
import com.siscofran.sehatq.ui.main.home.HomeFragment
import com.siscofran.sehatq.ui.main.profile.ProfileFragment
import com.siscofran.sehatq.ui.search.SearchActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        actionBar?.setDisplayShowTitleEnabled(false)
        nav.setOnNavigationItemSelectedListener(this)
        loadFragment(HomeFragment())

        edt_search.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when(item.itemId){
            R.id.nav_home -> {
                ic_heart.visibility = View.VISIBLE
                edt_search.visibility = View.VISIBLE
                fragment = HomeFragment()
            }
            R.id.nav_profile -> {
                ic_heart.visibility = View.GONE
                edt_search.visibility = View.GONE
                toolbar.title = getString(R.string.app_name)
                toolbar.setTitleTextColor(ContextCompat.getColor(application,R.color.white))
                setSupportActionBar(toolbar)
                fragment = ProfileFragment()
            }
        }
        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frm_layout, fragment)
                .commit()
            return true
        }
        return false
    }
}