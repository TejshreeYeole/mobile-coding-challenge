package com.example.traderevchallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CuratedPhotoModel(
    val color: String,
    val created_at: String,
    val current_user_collections: List<CurrentUserCollection>,
    val description: String?,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: Links,
    val updated_at: String,
    val urls: Urls,
    val user: User,
    val width: Int
) : Parcelable

@Parcelize
data class CurrentUserCollection(
    val cover_photo: Urls,
    val curated: Boolean,
    val id: Int,
    val published_at: String,
    val title: String,
    val updated_at: String,
    val user: User
) : Parcelable

@Parcelize
data class Links(
    val download: String,
    val download_location: String,
    val html: String,
    val self: String
) : Parcelable

@Parcelize
data class ProfileImage(
    val large: String,
    val medium: String,
    val small: String
) : Parcelable

@Parcelize
data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
) : Parcelable

@Parcelize
data class User(
    val bio: String?,
    val id: String,
    val instagram_username: String?,
    val links: LinksX,
    val location: String?,
    val name: String,
    val portfolio_url: String?,
    val profile_image: ProfileImage,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: String?,
    val username: String
) : Parcelable

@Parcelize
data class LinksX(
    val html: String,
    val likes: String,
    val photos: String,
    val portfolio: String,
    val self: String
) : Parcelable