package com.andres.rangel.vitalyn.hydration.ui.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.andres.rangel.vitalyn.hydration.R
import com.andres.rangel.vitalyn.hydration.ui.model.BarData
import com.andres.rangel.vitalyn.hydration.ui.model.UserHydrationLogUI
import com.andres.rangel.vitalyn.hydration.ui.model.UserWeeklyHydrationProgressUI
import com.andres.rangel.vitalyn.hydration.ui.viewmodel.HydrationViewModel
import com.andres.rangel.vitalyn.hydration.util.extension.getColor
import com.andres.rangel.vitalyn.hydration.util.extension.getIcon
import com.andres.rangel.vitalyn.hydration.util.extension.getInitialLetter
import com.andres.rangel.vitalyn.hydration.util.extension.getLabel
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun HydrationScreen(
    viewModel: HydrationViewModel = hiltViewModel()
) {
    val userStreakState by viewModel.userStreak.collectAsState()
    val userDailyGoalState by viewModel.userDailyGoal.collectAsState()
    val userCurrentHydrationLvlState by viewModel.userCurrentHydrationLvl.collectAsState()
    val userHydrationLogs by viewModel.userHydrationLogs.collectAsState()
    val userWeeklyHydrationProgress by viewModel.userWeeklyHydrationProgress.collectAsState()

    Scaffold(
        topBar = { TopAppBarSports(userStreakState) },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            CardDailyGoal(userDailyGoalState, userCurrentHydrationLvlState)
            Spacer(modifier = Modifier.height(10.dp))
            CardHydrationLog(userHydrationLogs)
            Spacer(modifier = Modifier.height(10.dp))
            CardWeeklyProgress(userWeeklyHydrationProgress)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBarSports(streak: Int) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.top_app_bar_hydration_title),
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
                        contentDescription = stringResource(R.string.top_app_bar_hydration_streak_icon),
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
fun CardDailyGoal(userDailyGoal: Int, userCurrentHydrationLvl: Int) {
    var progress by remember { mutableFloatStateOf(0.4f) }

    Text(
        text = stringResource(R.string.daily_goal_title),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(end = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$userDailyGoal ${stringResource(R.string.daily_goal_ml)}",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                LinearProgressIndicator(
                    progress = { progress }, modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    trackColor = MaterialTheme.colorScheme.secondary,
                    color = MaterialTheme.colorScheme.primaryContainer
                )

                Text(
                    text = "$userCurrentHydrationLvl ${stringResource(R.string.daily_goal_ml)}",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Column(
                modifier = Modifier.weight(0.2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottleFillAnimation(progress)
            }
        }
    }
}

@Composable
fun BottleFillAnimation(progress: Float) {
    val bottleWidth = 60.dp
    val bottleHeight = 110.dp

    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 200f),
        label = "waterFill"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val wavePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wavePhase"
    )

    Box(
        modifier = Modifier.size(width = bottleWidth, height = bottleHeight),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_bottle),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Image(
            painter = painterResource(id = R.drawable.water),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(width = bottleWidth, height = bottleHeight)
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    clip = true
                    shape = GenericShape { size, _ ->
                        val visibleHeight = size.height * animatedProgress
                        val waveAmplitude = 6f
                        val waveFrequency = 1f

                        moveTo(0f, size.height)
                        lineTo(0f, size.height - visibleHeight)

                        val topY = size.height - visibleHeight
                        for (x in 0..size.width.toInt() step 2) {
                            val normalizedX = x / size.width
                            val wave =
                                sin(normalizedX * waveFrequency * 2 * PI + wavePhase) * waveAmplitude
                            lineTo(x.toFloat(), topY + wave.toFloat())
                        }

                        lineTo(size.width, size.height)
                        close()
                    }
                }
        )
    }
}

@Composable
fun CardHydrationLog(logs: List<UserHydrationLogUI>) {
    Text(
        text = stringResource(R.string.hydration_log_title),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 5.dp)
        ) {
            items(logs) { log ->
                HydrationLogItem(
                    log = log
                )
            }
        }
    }
}

@Composable
private fun HydrationLogItem(
    log: UserHydrationLogUI
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(75.dp)
    ) {
        Box(
            modifier = Modifier
                .size(65.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(log.drinkType.getIcon()),
                contentDescription = "${stringResource(R.string.hydration_log_type)} ${log.drinkType.getLabel()}",
                modifier = Modifier.size(45.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${log.amount} ${stringResource(R.string.daily_goal_ml)}",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
        Text(
            text = log.formattedTime,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SimpleBarChart(
    data: List<BarData>,
    modifier: Modifier = Modifier
) {
    val maxValue = data.maxOfOrNull { it.value } ?: 0f

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            data.forEach { barData ->
                BarItem(
                    label = barData.label,
                    value = barData.value,
                    maxValue = maxValue,
                    color = barData.color,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun BarItem(
    label: String,
    value: Float,
    maxValue: Float,
    color: Color,
    modifier: Modifier = Modifier
) {
    val animatedHeight by animateFloatAsState(
        targetValue = value / maxValue,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "barHeight"
    )

    Column(
        modifier = modifier.padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "${value.toInt()} ${stringResource(R.string.daily_goal_ml)}",
            style = MaterialTheme.typography.labelSmall,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Box(
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(animatedHeight)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(color)
            )
        }

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun CardWeeklyProgress(userWeeklyHydrationProgress: List<UserWeeklyHydrationProgressUI>) {

    val weekData = arrayListOf<BarData>()
    for (progress in userWeeklyHydrationProgress) {
        weekData.add(
            BarData(
                label = progress.monthDay.getInitialLetter().toString(),
                value = progress.amount.toFloat(),
                color = progress.getColor()
            )
        )
    }

    Text(
        text = stringResource(R.string.weekly_progress_title),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 20.dp)
    ) {
        SimpleBarChart(
            data = weekData,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
