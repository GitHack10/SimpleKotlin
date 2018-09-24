package com.example.administrator.simplekotlin.ui.infouser

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.administrator.simplekotlin.R
import com.example.administrator.simplekotlin.data.models.User

class InfoUserActivity : AppCompatActivity() {

    private val EXTRA_USER = "INFO_USER"
    private lateinit var user: User

    companion object {

        fun getStartIntent(context: Context, user: User): Intent {
            val intent = Intent(context, InfoUserActivity::class.java)
            intent.putExtra(EXTRA_USER, user)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_user)

        user = intent.getParcelableExtra<User>(EXTRA_USER)
        setSupportActionBar(findViewById(R.id.toolbar_main))
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

        showInfoUserFragment(user)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        when (item.itemId) {
            android.R.id.home -> finish() // close this activity and return to preview activity (if there is any)
        }
        return true
    }


    private fun showInfoUserFragment(user: User?) {
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_USER, user)
        val infoUserFragment = InfoUserFragment()
        infoUserFragment.setArguments(bundle)

        supportFragmentManager.beginTransaction().replace(R.id.FrameLayout_infoUser_container, infoUserFragment).commit()
    }
}
