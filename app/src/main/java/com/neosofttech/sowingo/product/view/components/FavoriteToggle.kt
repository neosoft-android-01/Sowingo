package com.neosofttech.sowingo.product.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.neosofttech.sowingo.R

@Composable
fun FavoriteToggle(
    isFavourite: Boolean,
    productId: String,
    onToggleFavorites: (String) -> Unit
) {
    IconButton(
        onClick = {
            onToggleFavorites(productId)
        },
        modifier = Modifier
            .offset((-16).dp, (-16).dp)
            .shadow(elevation = 18.dp, shape = RoundedCornerShape(20.dp), clip = true)
    ) {
        val icon = if (isFavourite) {
            R.drawable.ic_fav
        } else {
            R.drawable.ic_default
        }
        Image(
            painter = painterResource(id = icon),
            modifier = Modifier
                .background(
                    colorResource(id = R.color.white),
                    shape = RoundedCornerShape(20.dp),
                )
                .size(40.dp)
                .padding(10.dp),
            contentDescription = ""
        )
    }
}