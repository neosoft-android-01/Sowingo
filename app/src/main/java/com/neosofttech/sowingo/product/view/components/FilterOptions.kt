package com.neosofttech.sowingo.product.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.neosofttech.sowingo.R
import com.neosofttech.sowingo.global.ALL
import com.neosofttech.sowingo.global.DEFAULT
import com.neosofttech.sowingo.global.FAVORITE

@Composable
fun FilterOptions(
    shouldShow: Boolean,
    onShowFilterOption: (Boolean) -> Unit,
    onOptionClick: (String) -> Unit
) {
    var expanded by remember{
        mutableStateOf(false)
    }
    Row {
        IconButton(onClick = { onShowFilterOption(true) }) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_filter_list_24),
                contentDescription = "filter",
                Modifier.clickable {
                    expanded = true
                }
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = {
            expanded = false
        },
            Modifier.clickable {
                onShowFilterOption(true)
            }
        ) {
            DropdownMenuItem(onClick = {
                onOptionClick(ALL)
                expanded = false
            }) {
                Text("All")
            }
            DropdownMenuItem(
                onClick = { onOptionClick(FAVORITE)
                    expanded = false
                }) {
                Text("Favourite")
            }
            DropdownMenuItem(
                onClick = { onOptionClick(DEFAULT)
                    expanded = false
                }) {
                Text("Default")
            }
        }
    }
}