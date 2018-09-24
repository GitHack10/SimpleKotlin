package com.example.administrator.simplekotlin.ui.listusers

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

class UserItemAdapter(private val users: List<User>, private var idFavoritesUsers: List<Int>,
                      private var onUsersItemListener: OnUsersItemListener,
                      private var addUserIconClickListener: AddUserIconClickListener) : RecyclerView.Adapter<UserItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(users[position])
    }

    override fun getItemId(position: Int): Long {
        return users[position].id.toLong()
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setIdFavoritesUsers(idFavoritesUsers: List<Int>) {
        this.idFavoritesUsers = idFavoritesUsers
    }

    fun setAddUserIconClickListener(addUserIconClickListener: (Any) -> Unit) {
        setAddUserIconClickListener(addUserIconClickListener)
    }

    interface OnUsersItemListener {
        fun onUserItemClick(user: User)
    }

    interface AddUserIconClickListener {
        fun addIconClick(user: User)
    }

    fun setOnUsersItemListener(onUsersItemListener: (Any) -> Unit) {
        setOnUsersItemListener(onUsersItemListener)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var userCircleImageView: CircleImageView = itemView.findViewById(R.id.ImageView_itemUser_icon)
        private var addUserImageView: ImageView = itemView.findViewById(R.id.ImageView_itemUser_addUser)

        private var userNameTextView: TextView = itemView.findViewById(R.id.TextView_itemUser_userName)
        private var userIdTextView: TextView = itemView.findViewById(R.id.TextView_itemUser_userId1)

        fun setData(userData: User) {

            Picasso.get().load(userData.avatarUrl).into(userCircleImageView)
            userNameTextView.text = userData.login
            userIdTextView.text = (userData.id).toString()

            for (i in idFavoritesUsers) {
                if (i == userData.id) {
                    addUserImageView.setImageResource(R.drawable.ic_check_user)
                    break
                } else { addUserImageView.setImageResource(R.drawable.ic_add_user) }
            }

            addUserImageView.setOnClickListener {

                View.OnClickListener {
                    if (addUserIconClickListener != null) addUserIconClickListener.addIconClick(userData)

                    idFavoritesUsers.plus(userData.id)

                    for (i in idFavoritesUsers) {
                        if (i == userData.id) {
                            addUserImageView.setImageResource(R.drawable.ic_check_user)
                            break
                        } else {
                            addUserImageView.setImageResource(R.drawable.ic_add_user)
                        }
                    }
                    Toast.makeText(itemView.context, itemView.context.resources
                            .getString(R.string.msg_addUser_in_favorites), Toast.LENGTH_SHORT).show()
                }
            }

            itemView.setOnClickListener {
                View.OnClickListener { if (onUsersItemListener != null) onUsersItemListener.onUserItemClick(userData) }
            }
        }
    }
}