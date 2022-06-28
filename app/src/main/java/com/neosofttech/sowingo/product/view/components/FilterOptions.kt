package com.neosofttech.sowingo.product.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import com.neosofttech.sowingo.R
import com.neosofttech.sowingo.global.ALL
import com.neosofttech.sowingo.global.DEFAULT
import com.neosofttech.sowingo.global.FAVORITE
import com.neosofttech.sowingo.ui.theme.dp_16

@Composable
fun FilterOptions(
    shouldShow: Boolean,
    onShowFilterOption: (Boolean) -> Unit,
    onOptionClick: (String) -> Unit
) {
    Row {
        IconButton(onClick = { onShowFilterOption(true) }) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_filter_list_24),
                contentDescription = "filter",
            )
        }

        if (shouldShow) {
            val all = buildAnnotatedString {
                append(stringResource(id = R.string.all))
            }
            ClickableText(
                text = all,
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { onOptionClick(ALL) })
            Spacer(modifier = Modifier.width(dp_16))
            val fav = buildAnnotatedString {
                append(stringResource(id = R.string.favorite))
            }
            ClickableText(
                text = fav,
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { onOptionClick(FAVORITE) })
            Spacer(modifier = Modifier.width(dp_16))
            val default = buildAnnotatedString {
                append(stringResource(id = R.string.unfavorite))
            }
            ClickableText(
                text = default,
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { onOptionClick(DEFAULT) })
        }
    }
}