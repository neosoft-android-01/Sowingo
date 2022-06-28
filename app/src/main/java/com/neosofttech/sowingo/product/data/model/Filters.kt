package com.neosofttech.sowingo.product.data.model

import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("Manufacturer Name")
    val manufacturerName: String,

    @SerializedName("Promotable Item Us")
    val promotableItemUs: String,

    @SerializedName("Step Approved")
    val stepApproved: String,

    @SerializedName("Package Quantity")
    val packageQuality: String,

    @SerializedName("Web Classification")
    val webClassification: String,

    @SerializedName("Total Volume")
    val totalVolume: String,

    @SerializedName("Case Quantity")
    val caseQuantity: String,

    @SerializedName("Promotable Item Ca")
    val promotableItem: String,

    @SerializedName("Enriched, Mktg Basic")
    val enrichedMktgBasic: String,

    @SerializedName("Enriched, Vendor")
    val enrichedVendor: String,

)