package com.neosofttech.sowingo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.neosofttech.sowingo.ui.theme.SowingoTheme
import com.neosofttech.sowingo.R
import com.neosofttech.sowingo.ui.theme.HeaderTextColor
import com.neosofttech.sowingo.ui.theme.TopBarHeader
import com.neosofttech.sowingo.ui.theme.dp_4


@Composable
fun AppContainer(
    headerText: String = stringResource(id = R.string.app_name),
    content: @Composable () -> Unit
) {
    SowingoTheme {
        Column {
            TopBar(headerText)
            content()
        }
    }
}

@Preview
@Composable
fun PreviewAppContainer() {
    SowingoTheme {
        TopBar(stringResource(id = R.string.products))
    }
}

@Composable
private fun TopBar(headerText: String) {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = dp_4,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            HeaderText(headerText)
        }
    }
}

@Composable
private fun HeaderText(headerText: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = headerText,
        textAlign = TextAlign.Center,
        color = HeaderTextColor,
        style = TopBarHeader
    )
}
