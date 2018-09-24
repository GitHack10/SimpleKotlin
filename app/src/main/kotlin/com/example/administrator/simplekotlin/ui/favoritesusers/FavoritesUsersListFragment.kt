package com.example.administrator.simplekotlin.ui.favoritesusers

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.administrator.simplekotlin.App
import com.example.administrator.simplekotlin.R
import com.example.administrator.simplekotlin.data.models.User
import com.example.administrator.simplekotlin.mvp.favoritesusers.FavoritesUsersListPresenter
import com.example.administrator.simplekotlin.mvp.favoritesusers.FavoritesUsersListView
import com.example.administrator.simplekotlin.ui.infouser.InfoUserActivity


class FavoritesUsersListFragment : Fragment(), FavoritesUsersListView {

    private lateinit var favoritesUsersRecyclerView: RecyclerView
    private lateinit var favoriteUserItemAdapter: FavoriteUserItemAdapter

    private lateinit var presenter: FavoritesUsersListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_users_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        favoritesUsersRecyclerView = view.findViewById(R.id.RecyclerView_fragmentFavoritesUsers_user)
        favoritesUsersRecyclerView.layoutManager = LinearLayoutManager(context)
        presenter = FavoritesUsersListPresenter(App.dataManager)
        presenter.attachView(this)
        presenter.getFavoritesUsers()
    }

    override fun showFavoritesUsers(favoritesUsers: List<User>) {
        favoriteUserItemAdapter = FavoriteUserItemAdapter(favoritesUsers)
        favoriteUserItemAdapter.setOnFavoritesUsersItemListener { favoriteUser -> presenter.itemClick(favoriteUser as User) }
        favoriteUserItemAdapter.setOnRemoveItemListener { favoriteUser -> presenter.deleteUser(favoriteUser as User) }
        favoritesUsersRecyclerView.adapter = favoriteUserItemAdapter
    }

    override fun startInfoActivity(user: User) {
        startActivityForResult(InfoUserActivity.getStartIntent(this!!.context!!, user), REQUEST_CODE_USER_INFO)
    }

    override fun showProgress(show: Boolean) {}

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val REQUEST_CODE_USER_INFO = 1
    }
}