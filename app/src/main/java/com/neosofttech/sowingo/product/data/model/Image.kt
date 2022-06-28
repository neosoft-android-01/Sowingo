package com.neosofttech.sowingo.product.data.model

import com.google.gson.annotations.SerializedName

data class Image(
    val image: String,
    @SerializedName("image_240_box")
    val image240Box: String?,
    @SerializedName("image_240_wide")
    val image240Wide: String?,
    @SerializedName("main_image_bool")
    val mainImage: Boolean?
)