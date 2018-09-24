package com.example.administrator.simplekotlin.ui.listusers

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.administrator.simplekotlin.App
import com.example.administrator.simplekotlin.R
import com.example.administrator.simplekotlin.data.models.User
import com.example.administrator.simplekotlin.mvp.listusers.UsersListPresenter
import com.example.administrator.simplekotlin.mvp.listusers.UsersListView
import com.example.administrator.simplekotlin.ui.infouser.InfoUserActivity
import retrofit2.Call

class UsersListFragment : Fragment(), UsersListView {
    private lateinit var presenter: UsersListPresenter
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userItemAdapter: UserItemAdapter
    private lateinit var progressBar: ProgressBar
    private val usersCall: Call<List<User>>? = null

    private val REQUEST_CODE_USER_INFO = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userRecyclerView = view.findViewById(R.id.RecyclerView_main_user)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(userRecyclerView.context, (userRecyclerView.layoutManager as LinearLayoutManager).orientation)
        userRecyclerView.addItemDecoration(dividerItemDecoration)
        progressBar = view.findViewById(R.id.progress_main)
        presenter = UsersListPresenter(App.dataManager)
        presenter.attachView(this)
        presenter.getAllUsers()
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showUsersList(users: List<User>, idFavoritesUsers: List<Int>) {
        userItemAdapter = UserItemAdapter(users, idFavoritesUsers, null, null)
        userItemAdapter.setOnUsersItemListener { user -> presenter.itemClick(user as User) }
        userItemAdapter.setAddUserIconClickListener { user -> presenter.insertUser(user as User) }
        userRecyclerView.adapter = userItemAdapter
    }

    override fun startInfoActivity(user: Any) {
        startActivityForResult(InfoUserActivity.getStartIntent(this.context!!, user as User), REQUEST_CODE_USER_INFO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == MainActivity.REQUEST_CODE_FAVORITES) {
            userItemAdapter.setIdFavoritesUsers(presenter.idFavoritesUsers())
            userItemAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        usersCall!!.cancel()
    }
}