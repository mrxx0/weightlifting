package com.mrxx0.weightlifting.presentation.set

import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mrxx0.weightlifting.R
import com.mrxx0.weightlifting.domain.model.Set

@Composable
fun SetCard(
    set: Set
) {

    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(IntrinsicSize.Max)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Row {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Column {
                    Text(
                        text = context.resources.getString(R.string.set_repetitions),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = set.repetitions.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .width(2.dp)
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
            )
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Column {
                    Text(
                        text = context.resources.getString(R.string.set_weight),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = set.weight.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .width(2.dp)
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
            )
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Column {
                    Text(
                        text = context.resources.getString(R.string.set_rest_time),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = DateUtils.formatElapsedTime(set.restTime.toLong()),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}
