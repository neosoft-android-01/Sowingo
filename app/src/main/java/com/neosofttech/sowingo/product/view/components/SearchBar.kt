package com.neosofttech.sowingo.product.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neosofttech.sowingo.R
import com.neosofttech.sowingo.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = dp_16, vertical = dp_10)
            .fillMaxWidth(),
        shape = Shapes.medium,
        color = SearchBg,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = dp_16, vertical = dp_8)
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            TextField(
                value = searchInput,
                onValueChange = {
                    onSearchInputChanged(it)
                },
                modifier = Modifier.align(Alignment.CenterVertically),
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search_bar),
                        contentDescription = "Search",
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.searchbar_hint),
                        overflow = TextOverflow.Ellipsis,
                        style = SearchHintText,
                        maxLines = 1,
                        modifier = Modifier.padding(0.dp, 0.dp),
                    )
                },
                textStyle = SearchHintText,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ), // keyboardOptions change the newline key to a search key on the soft keyboard
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                // keyboardActions submits the search query when the search key is pressed
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchInputChanged(searchInput)
                        keyboardController?.hide()
                    }
                ),
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun PreviewSearchBar() {
    Surface(
        modifier = Modifier
            .padding(horizontal = dp_16, vertical = dp_10)
            .fillMaxWidth(),
        shape = Shapes.medium,
        color = SearchBg,
    ) {

        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            value = "searchInput",
            onValueChange = {

            },
            modifier = Modifier
                //.align(Alignment.CenterVertically)
                .background(Color.Blue),
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_bar),
                    contentDescription = "Search",
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_filter_list_24),
                    contentDescription = "Search",
                    Modifier.padding(dp_0)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.searchbar_hint),
                    overflow = TextOverflow.Ellipsis,
                    style = SearchHintText,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(0.dp, 0.dp)
                        .background(Color.Cyan),
                )
            },
            textStyle = SearchHintText,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ), // keyboardOptions change the newline key to a search key on the soft keyboard
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            // keyboardActions submits the search query when the search key is pressed
            keyboardActions = KeyboardActions(
                onSearch = {
                    //onSearchInputChanged(searchInput)
                    keyboardController?.hide()
                }
            ),
        )

        /*Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = dp_16, vertical = dp_8).fillMaxWidth().background(
                Color.Green)
        ) {


        }*/
    }
}

