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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.andres.rangel.vitalyn.core.util.DataState
import com.andres.rangel.vitalyn.sport.R
import com.andres.rangel.vitalyn.sport.ui.viewmodel.SportsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsScreen(
    viewModel: SportsViewModel = hiltViewModel()
) {
    val streak by viewModel.streak.collectAsState()

    Scaffold(
        topBar = {
            TopAppBarSports(streak)
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            TrainingCalendarHeader()
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBarSports(streak: DataState<Int>) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.top_app_bar_sports_title),
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
                    when (streak) {
                        is DataState.Success -> Text(
                            text = streak.data.toString(),
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        is DataState.Loading -> CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(end = 5.dp)
                        )
                        is DataState.Error -> Text(
                            text = stringResource(R.string.top_app_bar_sports_error_streak),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
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
}

@Composable
fun TrainingCalendarHeader() {
    val weekDaysShort = stringArrayResource(R.array.week_days_short)
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
                start = 30.dp,
                end = 30.dp,
                top = 10.dp
            )
    ) {
        weekDaysShort.forEach { day ->
            Text(
                text = day,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 5.dp
            )
    ) {
        for (day in 0..6) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(60.dp)
            ) {
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

val numberDays = listOf(
    "10", "11", "12", "13", "14", "15", "16"
)

@Preview
@Composable
fun PreviewSports() {
    SportsScreen()
}
