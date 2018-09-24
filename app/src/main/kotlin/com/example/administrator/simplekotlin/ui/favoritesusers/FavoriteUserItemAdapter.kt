package com.example.administrator.simplekotlin.ui.favoritesusers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.administrator.simplekotlin.R
import com.example.administrator.simplekotlin.data.models.User
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class FavoriteUserItemAdapter(private val favoritesUsers: List<User>) : RecyclerView.Adapter<FavoriteUserItemAdapter.FavoritesUsersItemViewHolder>() {

    private lateinit var onRemoveItemListener: OnRemoveItemListener
    private lateinit var onFavoritesUsersItemListener: OnFavoritesUsersItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesUsersItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_user, parent, false)
        return FavoritesUsersItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoritesUsersItemViewHolder, position: Int) {
        holder.setFavoritesUsersData(favoritesUsers[position])
    }

    override fun getItemCount(): Int {
        return favoritesUsers.size
    }

    fun setOnRemoveItemListener(onRemoveItemListener: (Any) -> Unit) {
        setOnRemoveItemListener(onRemoveItemListener)
    }

    fun setOnFavoritesUsersItemListener(onFavoritesUsersItemListener: (Any) -> Unit) {
        setOnFavoritesUsersItemListener(onFavoritesUsersItemListener)
    }

    interface OnFavoritesUsersItemListener {
        fun onFavoriteUserItemClick(user: User)
    }

    interface OnRemoveItemListener {
        fun onRemoveItemClick(favoriteUser: User)
    }

    inner class FavoritesUsersItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val loginTextView: TextView
        private val idTextView: TextView
        private val avatarCircleImageView: CircleImageView
        private val removeImageView: ImageView

        init {

            loginTextView = itemView.findViewById(R.id.TextView_itemFavoriteUser_userName)
            idTextView = itemView.findViewById(R.id.TextView_itemFavoriteUser_userId1)
            avatarCircleImageView = itemView.findViewById(R.id.ImageView_itemFavoriteUser_icon)
            removeImageView = itemView.findViewById(R.id.ImageView_itemFavoriteUser_removeUser)
        }

        fun setFavoritesUsersData(favoriteUserData: User) {
            Picasso.get().load(favoriteUserData.avatarUrl).into(avatarCircleImageView)
            loginTextView.setText(favoriteUserData.login)
            idTextView.setText((favoriteUserData.id).toString())
            removeImageView.setOnClickListener { view ->
                if (onRemoveItemListener != null) onRemoveItemListener!!.onRemoveItemClick(favoriteUserData)
                Toast.makeText(itemView.context, itemView.context.resources
                        .getString(R.string.msg_removeUser_in_favorites), Toast.LENGTH_SHORT).show()
            }
            itemView.setOnClickListener { view -> if (onFavoritesUsersItemListener != null) onFavoritesUsersItemListener!!.onFavoriteUserItemClick(favoriteUserData) }
        }
    }
}