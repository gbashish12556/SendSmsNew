package com.test.ashish.sendsmsnew

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    private var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(ContactFragment())

        val navigation = findViewById<BottomNavigationView>(R.id.activity_sales_bottom_navigation_view)
        navigation.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.contacts -> fragment = ContactFragment()

                R.id.messages -> fragment = MessageFragment()

                else -> {
                }
            }

            loadFragment(fragment)
        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_sales_frame_layout, fragment)
                    .commit()

            return true
        }
        return false
    }
}