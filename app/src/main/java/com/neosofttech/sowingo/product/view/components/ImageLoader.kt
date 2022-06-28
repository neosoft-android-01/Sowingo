package com.neosofttech.sowingo.product.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.neosofttech.sowingo.R
import com.neosofttech.sowingo.ui.theme.*

@Composable
fun ImageLoader(url: String?) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "",
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(id = R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder),
        fallback = painterResource(id = R.drawable.ic_placeholder),
        modifier = Modifier.requiredSize(100.dp)
    )
}

@Preview
@Composable
fun PreviewProductItem() {

    Surface {
        Row(
            modifier = Modifier.padding(dp_16)
        ) {

            Box (Modifier.background(Color.Green)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_placeholder),
                    contentDescription = "",
                    modifier = Modifier.requiredSize(100.dp)
                )
                FavoriteToggle(
                    productId = "product.id",
                    isFavourite = false,
                    onToggleFavorites = { }
                )
            }

            Column(
                modifier = Modifier.weight(1f).background(Color.Cyan)
            ) {
                Text(
                    text = "dhjd khkdhg jkydikhgk khfgkfhgk",
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    style = description,
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "$99.000",
                        style = price1,
                        modifier = Modifier
                            .padding(dp_0, dp_12, dp_0, dp_0)
                            .align(Alignment.Bottom),
                    )
                    Text(
                        text = "$178.90",
                        style = price2,
                        modifier = Modifier.padding(dp_8, dp_16, dp_0, dp_0),
                        textDecoration = TextDecoration.LineThrough,
                    )
                }
            }
        }
    }
}