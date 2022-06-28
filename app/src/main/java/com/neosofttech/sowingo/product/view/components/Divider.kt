package com.neosofttech.sowingo.product.view.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.neosofttech.sowingo.R
import com.neosofttech.sowingo.ui.theme.dp_1

@Composable
fun ListDivider() {
    Divider(
        Modifier.height(dp_1),
        color = colorResource(id = R.color.product_divider)
    )
}