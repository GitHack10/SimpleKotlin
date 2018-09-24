package com.example.administrator.simplekotlin.ui.listusers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.administrator.simplekotlin.R
import com.example.administrator.simplekotlin.ui.favoritesusers.FavoritesUsersActivity

class MainActivity : AppCompatActivity() {

    companion object {

        private val TAG = "tag"
        val REQUEST_CODE_FAVORITES = 2
    }

    internal lateinit var usersListFragment: UsersListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        usersListFragment = supportFragmentManager.findFragmentByTag(TAG) as UsersListFragment

        if (usersListFragment == null) {
            usersListFragment = UsersListFragment()
            supportFragmentManager.beginTransaction().add(R.id.FrameLayout_main_container, usersListFragment!!, TAG).commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_main_favoritesUsers -> supportFragmentManager.findFragmentByTag(TAG)!!.startActivityForResult(FavoritesUsersActivity
                    .getStartIntent(this@MainActivity), REQUEST_CODE_FAVORITES)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }
}