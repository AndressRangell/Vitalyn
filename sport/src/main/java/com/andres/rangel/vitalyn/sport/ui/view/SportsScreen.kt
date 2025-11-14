package com.andres.rangel.vitalyn.sport.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.andres.rangel.vitalyn.core.util.DataState
import com.andres.rangel.vitalyn.sport.R
import com.andres.rangel.vitalyn.sport.domain.model.Routine
import com.andres.rangel.vitalyn.sport.ui.viewmodel.SportsViewModel
import com.andres.rangel.vitalyn.sport.utils.CalendarUtils
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsScreen(
    viewModel: SportsViewModel = hiltViewModel()
) {
    val streakState by viewModel.streak.collectAsState()
    val routinesState by viewModel.routines.collectAsState()

    val streak = when (streakState) {
        is DataState.Success -> (streakState as DataState.Success<Int>).data
        else -> 0
    }

    val routines = when (routinesState) {
        is DataState.Success -> (routinesState as DataState.Success<List<Routine>>).data
        else -> emptyList()
    }

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
            RoutinesList(routines)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBarSports(streak: Int) {
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
                    Text(
                        text = streak.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                    Icon(
                        painterResource(R.drawable.streak),
                        contentDescription = stringResource(R.string.top_app_bar_sports_streak_icon),
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

    var weekOffset by remember { mutableStateOf(0) }
    val baseDate = LocalDate.now().plusWeeks(weekOffset.toLong())
    val week = remember(baseDate) {
        CalendarUtils.getWeekMondayToSunday(baseDate)
    }

    val calendarTitle = CalendarUtils.getCalendarTitle(week)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    ) {
        Text(
            calendarTitle,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = { weekOffset-- },
            modifier = Modifier
                .width(30.dp)
                .height(20.dp)
                .padding(end = 10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.previous),
                contentDescription = stringResource(R.string.training_calendar_header_icon_previous)
            )
        }
        IconButton(
            onClick = { weekOffset++ },
            modifier = Modifier.size(20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.previous),
                contentDescription = stringResource(R.string.training_calendar_header_icon_next),
                modifier = Modifier.scale(scaleX = -1f, scaleY = 1f)
            )
        }
    }
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
        week.forEach { dayItem ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(40.dp)
            ) {
                val textColor =
                    if (dayItem.isToday) MaterialTheme.colorScheme.background
                    else MaterialTheme.colorScheme.primary

                val backgroundColor =
                    if (dayItem.isToday) MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.background

                val weight =
                    if (dayItem.isToday) FontWeight.Bold
                    else FontWeight.Normal

                Text(
                    text = dayItem.day.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor,
                    fontWeight = weight,
                    modifier = Modifier
                        .size(32.dp)
                        .background(backgroundColor, CircleShape)
                        .padding(
                            top = 3.dp,
                            start = 7.dp
                        )
                )
            }
        }
    }
}

@Composable
fun RoutinesList(routines: List<Routine>) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        routines.forEach { routine ->
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                elevation = CardDefaults.cardElevation(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    )
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                vertical = 20.dp,
                                horizontal = 25.dp
                            )
                            .weight(0.6f)
                    ) {
                        val textStatus =
                            if (routine.progress != 100) stringResource(R.string.routine_list_status_routine)
                            else stringResource(R.string.routine_list_status_completed)

                        Text(
                            text = textStatus,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )
                        Text(
                            text = routine.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                        ) {
                            Text(
                                text = routine.category,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.padding(end = 15.dp)
                            )
                            Text(
                                text = routine.duration.toString() + stringResource(R.string.routine_list_mins),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .height(60.dp)
                        ) {
                            Text(
                                stringResource(R.string.routine_list_start),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(0.4f)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.cycling),
                            contentDescription = stringResource(R.string.routine_list_image_description),
                            alignment = Alignment.BottomEnd,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .padding(top = 10.dp),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            contentAlignment = Alignment.TopEnd,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                val backgroundProgress = MaterialTheme.colorScheme.onSurfaceVariant
                                val indicateProgress = MaterialTheme.colorScheme.surfaceVariant
                                Canvas(
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    drawArc(
                                        color = backgroundProgress,
                                        startAngle = -90f,
                                        sweepAngle = 360f,
                                        useCenter = false,
                                        style = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round)
                                    )
                                    drawArc(
                                        color = indicateProgress,
                                        startAngle = -90f,
                                        sweepAngle = -(360f * routine.progress / 100),
                                        useCenter = false,
                                        style = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round)
                                    )
                                }
                                Text(
                                    text = routine.progress.toString(),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 20.dp,
                                            vertical = 25.dp
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
        Button(
            onClick = {},
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .height(60.dp)
                .padding(
                    horizontal = 20.dp,
                    vertical = 10.dp
                )
        ) {
            Text(
                stringResource(R.string.routine_list_add_new_routine),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
