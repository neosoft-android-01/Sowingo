package com.neosofttech.sowingo.product.data.model

import com.google.gson.annotations.SerializedName

data class AdvertisingBadges(
    val badges: List<Badge>,
    @SerializedName("has_badge")
    val hasBadge: Boolean?
)

data class Badge(
    @SerializedName("badge_type")
    val badgeType: String?,
    @SerializedName("badge_image_url")
    val badgeImageUrl: String?,
)