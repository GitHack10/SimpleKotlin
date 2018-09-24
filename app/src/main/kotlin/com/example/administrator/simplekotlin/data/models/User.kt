package com.example.administrator.simplekotlin.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity
data class User(@PrimaryKey @SerializedName("id") var id: Int,
                @SerializedName("login") var login: String,
                @SerializedName("avatar_url") var avatarUrl: String,
                @SerializedName("location") var location: String,
                @SerializedName("name") var name: String,
                @SerializedName("public_repos") var publicRepos: String,
                @SerializedName("followers") var followers: String,
                @SerializedName("following") var following: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

//    constructor(id: Int, login: String, avatarUrl: String, location: String, name: String, publicRepos: String, followers: String, following: String) {
//        this.id = id
//        this.login = login
//        this.avatarUrl = avatarUrl
//        this.location = location
//        this.name = name
//        this.publicRepos = publicRepos
//        this.followers = followers
//        this.following = following
//    }

//    protected constructor(`in`: Parcel) {
//        id = `in`.readInt()
//        login = `in`.readString()
//        avatarUrl = `in`.readString()
//        location = `in`.readString()
//        name = `in`.readString()
//        publicRepos = `in`.readString()
//        followers = `in`.readString()
//        following = `in`.readString()
//    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(login)
        parcel.writeString(avatarUrl)
        parcel.writeString(location)
        parcel.writeString(name)
        parcel.writeString(publicRepos)
        parcel.writeString(followers)
        parcel.writeString(following)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}