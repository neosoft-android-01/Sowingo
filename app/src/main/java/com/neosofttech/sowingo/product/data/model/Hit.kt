package com.neosofttech.sowingo.product.data.model

import com.google.gson.annotations.SerializedName


data class Hit(
    @SerializedName("unit_name")
    val unitName: String?,
    val subcategory: Category,
    val objectID: String?,
    @SerializedName("medical_field_id")
    val medicalFieldId: String?,
    @SerializedName("content_per_unit")
    val contentPerUnit: String?,
    val filters: Filters,
    val images: List<Image>,
    @SerializedName("on_hand")
    val onHand: Int,
    val id: String,
    @SerializedName("main_image_240_box")
    val mainImage240Box: String?,
    val barcodes: String?,
    @SerializedName("vendor_inventory")
    val vendorInventory: List<VendorInventoryX>,
    @SerializedName("country_id")
    val countryId: String?,
    @SerializedName("tracking_method")
    val trackingMethod: String?,
    @SerializedName("is_favourite_product")
    val isFavouriteProduct: Boolean,
    @SerializedName("advertising_badges")
    val advertisingBadges: AdvertisingBadges,
    @SerializedName("order_package_quantity")
    val orderPackageQuantity: Int,
    val description: String,
    @SerializedName("marketplace_id")
    val marketplaceId: String?,
    @SerializedName("parent_category")
    val parentCategory: Category,
    @SerializedName("buy_by_case")
    val buyByCase: Boolean,
    @SerializedName("manufacturer_sku")
    val manufacturerSku: String?,
    val manufacturer: Manufacturer,
    @SerializedName("main_image")
    val mainImage: String?,
    val name: String,
    @SerializedName("sds_url")
    val sdsUrl: List<String>?,
    @SerializedName("office_inventory_item_id")
    val officeInventoryItemId: String,
    @SerializedName("item_type")
    val itemType: String,
    @SerializedName("main_image_240_wide")
    val mainImage240Wide: String?,
    @SerializedName("_id")
    val _id: String
)