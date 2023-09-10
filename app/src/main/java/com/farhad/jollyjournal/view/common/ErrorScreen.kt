package com.farhad.jollyjournal.com.farhad.jollyjournal.view.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farhad.jollyjournal.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    retry: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(36.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = modifier.size(128.dp),
            imageVector = Icons.Filled.SignalWifiConnectedNoInternet4,
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.error_message),
            textAlign = TextAlign.Center
        )
        retry?.let {
            Button(onClick = retry) {
                Text(text = "Retry")
            }
        }
    }
}