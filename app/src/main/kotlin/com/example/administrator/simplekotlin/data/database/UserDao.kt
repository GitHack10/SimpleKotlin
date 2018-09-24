package com.example.administrator.simplekotlin.data.database

import android.arch.persistence.room.*
import com.example.administrator.simplekotlin.data.models.User

@Dao
interface UserDao {

    @get:Query("SELECT * FROM User")
    val getUsers: List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteUser: User)

    @Delete
    fun delete(favoriteUser: User)
}