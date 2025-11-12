package com.andres.rangel.vitalyn.sport.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andres.rangel.vitalyn.sport.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tu Actividad",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.width(70.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.width(50.dp)
                        ) {
                            Text(
                                text = "12",
                                style = MaterialTheme.typography.titleSmall,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(vertical = 5.dp)
                            )
                            Icon(
                                painterResource(R.drawable.streak),
                                contentDescription = "Streak icon",
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier
                                    .size(25.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                "Noviembre 2025",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 25.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp,
                        horizontal = 25.dp
                    )
            ) {
                for (day in 0..6) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.height(60.dp)
                    ) {
                        Text(
                            text = days[day].toString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = numberDays[day],
                            style = MaterialTheme.typography.titleMedium,
                            color = if (numberDays[day] == "10")
                                MaterialTheme.colorScheme.background
                            else
                                MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .size(32.dp)
                                .background(
                                    color = if (numberDays[day] == "10")
                                        MaterialTheme.colorScheme.primaryContainer
                                    else
                                        MaterialTheme.colorScheme.background,
                                    shape = CircleShape
                                )
                                .padding(
                                    top = 3.dp,
                                    start = 7.dp
                                )
                        )
                    }
                }
            }
        }
    }
}

val days = listOf(
    'L', 'M', 'M', 'J', 'V', 'S', 'D'
)
val numberDays = listOf(
    "10", "11", "12", "13", "14", "15", "16"
)

@Preview
@Composable
fun PreviewSports() {
    SportsScreen()
}
