package com.mrxx0.weightlifting.presentation.session.ui.detailsscreen

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrxx0.weightlifting.presentation.session.SessionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSessionDetailsScreen(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    onNavigationIconClick: (() -> Unit?)? = null,
    onActionClick: (() -> Unit?)? = null
) {
    val sessionViewModel = hiltViewModel<SessionViewModel>()
    val sessionDeleteMode by sessionViewModel.sessionDeleteMode.observeAsState()

    CenterAlignedTopAppBar(
        navigationIcon = {},
        actions = {
            IconButton(onClick = {
                if (onActionClick != null) {
                    // navigate to screen to edit title
                    onActionClick()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        modifier = modifier
            .height(IntrinsicSize.Max)
            .wrapContentHeight(align = Alignment.CenterVertically),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
    )
}