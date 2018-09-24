package com.example.administrator.simplekotlin.ui.infouser

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.administrator.simplekotlin.App
import com.example.administrator.simplekotlin.R
import com.example.administrator.simplekotlin.data.models.User
import com.example.administrator.simplekotlin.mvp.infouser.InfoUserPresenter
import com.example.administrator.simplekotlin.mvp.infouser.InfoUserView
import com.squareup.picasso.Picasso


@SuppressLint("ValidFragment")
class InfoUserFragment : Fragment(), InfoUserView {
    private var presenter: InfoUserPresenter? = null
    private var avatarImageView: ImageView? = null
    private var user: User? = null

    private var loginTextView: TextView? = null
    private var nameTextView: TextView? = null
    private var locationTextView: TextView? = null
    private var publicRepos: TextView? = null
    private var followersTextView: TextView? = null
    private var followingTextView: TextView? = null

    private var defaultLoginTextView: TextView? = null
    private var defaultNameTextView: TextView? = null
    private var defaultLocationTextView: TextView? = null
    private var defaultPublicRepos: TextView? = null
    private var defaultFollowersTextView: TextView? = null
    private var defaultFollowingTextView: TextView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inItViews(view)

        if (arguments != null) {
            user = arguments!!.getParcelable<User>(EXTRA_USER)
        }

        presenter = InfoUserPresenter(App.dataManager!!)
        presenter!!.attachView(this)
        presenter!!.getUser(user!!.login)
    }

    private fun inItViews(view: View) {
        avatarImageView = view.findViewById(R.id.ImageView_user_info_avatar)
        progressBar = view.findViewById(R.id.progress_info)

        loginTextView = view.findViewById(R.id.TextView_user_info_login)
        nameTextView = view.findViewById(R.id.TextView_user_info_name)
        locationTextView = view.findViewById(R.id.TextView_user_info_location)
        publicRepos = view.findViewById(R.id.TextView_user_info_public_repos)
        followersTextView = view.findViewById(R.id.TextView_user_info_followers)
        followingTextView = view.findViewById(R.id.TextView_user_info_following)

        defaultLoginTextView = view.findViewById(R.id.TextView_user_info_defaultLogin)
        defaultNameTextView = view.findViewById(R.id.TextView_user_info_defaultName)
        defaultLocationTextView = view.findViewById(R.id.TextView_user_info_defaultLocation)
        defaultPublicRepos = view.findViewById(R.id.TextView_user_info_public_defaultRepos)
        defaultFollowersTextView = view.findViewById(R.id.TextView_user_info_defaultFollowers)
        defaultFollowingTextView = view.findViewById(R.id.TextView_user_info_defaultFollowing)
    }

    private fun setData(userData: User) {
        Picasso.get().load(user!!.avatarUrl).into(avatarImageView)

        defaultLoginTextView!!.visibility = View.VISIBLE
        defaultNameTextView!!.visibility = View.VISIBLE
        defaultLocationTextView!!.visibility = View.VISIBLE
        defaultPublicRepos!!.visibility = View.VISIBLE
        defaultFollowersTextView!!.visibility = View.VISIBLE
        defaultFollowingTextView!!.visibility = View.VISIBLE

        loginTextView!!.setText(userData.login)
        nameTextView!!.setText(userData.name)
        locationTextView!!.setText(userData.location)
        publicRepos!!.setText(userData.publicRepos)
        followersTextView!!.setText(userData.followers)
        followingTextView!!.setText(userData.following)
    }

    override fun showProgress(show: Boolean) {
        progressBar!!.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {

    }

    override fun showInfoUser(user: User) {
        setData(user)
    }

    companion object {

        private val EXTRA_USER = "INFO_USER"
    }
}