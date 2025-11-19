package com.andres.rangel.vitalyn.app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andres.rangel.vitalyn.R
import com.andres.rangel.vitalyn.app.navigation.NavigationItem
import com.andres.rangel.vitalyn.app.navigation.Screen
import com.andres.rangel.vitalyn.app.util.AppConstants
import com.andres.rangel.vitalyn.core.ui.theme.GrayLight
import com.andres.rangel.vitalyn.core.ui.theme.GreenPastel

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 25.dp
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            shape = RoundedCornerShape(35.dp),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                navigationItems.forEach { item ->
                    val selected = currentDestination?.route == item.route

                    BottomNavigationItem(item = item, selected = selected) {
                        if (!selected) navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationItem(item: NavigationItem, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) GreenPastel.copy(alpha = 0.1f) else Color.Transparent,
        animationSpec = tween(150, easing = LinearOutSlowInEasing),
        label = stringResource(R.string.bottom_navigation_item_background_animation_label)
    )

    val scale by animateFloatAsState(
        targetValue = if (selected) 1.15f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = stringResource(R.string.bottom_navigation_item_scale_animation_label)
    )

    Box(
        modifier = Modifier
            .size(64.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(55.dp)
                .background(backgroundColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = item.title,
                modifier = Modifier
                    .size(if (item.title == AppConstants.SPORTS) 30.dp else 26.dp)
                    .graphicsLayer(scaleX = scale, scaleY = scale),
                tint = if (selected) GreenPastel else GrayLight
            )
        }
    }
}

val navigationItems = listOf(
    NavigationItem(
        title = AppConstants.SPORTS,
        icon = R.drawable.gym,
        route = Screen.Sports.route
    ),
    NavigationItem(
        title = AppConstants.NUTRITION,
        icon = R.drawable.nutrition,
        route = Screen.Nutrition.route
    ),
    NavigationItem(
        title = AppConstants.REST,
        icon = R.drawable.rest,
        route = Screen.Rest.route
    ),
    NavigationItem(
        title = AppConstants.HYDRATION,
        icon = R.drawable.watter,
        route = Screen.Hydration.route
    ),
    NavigationItem(
        title = AppConstants.SETTINGS,
        icon = R.drawable.settings,
        route = Screen.Settings.route
    )
)
