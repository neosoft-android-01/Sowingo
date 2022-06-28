package com.neosofttech.sowingo.product.data.model

import com.google.gson.annotations.SerializedName

data class VendorInventoryX(
    @SerializedName("vendor_inventory_id")
    val vendorInventoryId: String?,
    @SerializedName("list_price")
    val listPrice: Double?,
    @SerializedName("marketplace_id")
    val marketplaceId: String?,
    val price: Double,
    @SerializedName("special_fee")
    val specialFee: String,
    @SerializedName("special_fee_amount")
    val specialFeeAmount: Any?,
    @SerializedName("vendor_sku")
    val vendorSku: String?,
    @SerializedName("has_promotions")
    val hasPromotions: Boolean?,
    val vendor: Vendor,
    @SerializedName("is_published")
    val isPublished: Boolean?
)

data class Vendor(
    val id: String,
    val image: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    val name: String
)