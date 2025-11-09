package com.andres.rangel.vitalyn.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andres.rangel.vitalyn.R
import com.andres.rangel.vitalyn.navigation.NavigationItem
import com.andres.rangel.vitalyn.navigation.Screen
import com.andres.rangel.vitalyn.ui.theme.GrayDark
import com.andres.rangel.vitalyn.ui.theme.GrayLight
import com.andres.rangel.vitalyn.ui.theme.GreenPastel

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    horizontalInset: Dp = 20.dp,
    verticalInset: Dp = 25.dp,
    cornerRadius: Dp = 35.dp
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalInset, vertical = verticalInset),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            shape = RoundedCornerShape(cornerRadius),
            tonalElevation = 8.dp,
            shadowElevation = 8.dp,
            color = GrayDark,
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
        label = "background animation"
    )

    val scale by animateFloatAsState(
        targetValue = if (selected) 1.15f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "scaleAnim"
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
                    .size(if (item.title == "Sports") 30.dp else 26.dp)
                    .graphicsLayer(scaleX = scale, scaleY = scale),
                tint = if (selected) GreenPastel else GrayLight
            )
        }
    }
}

val navigationItems = listOf(
    NavigationItem(
        title = "Sports",
        icon = R.drawable.gym,
        route = Screen.Sports.route
    ),
    NavigationItem(
        title = "Nutrition",
        icon = R.drawable.nutrition,
        route = Screen.Nutrition.route
    ),
    NavigationItem(
        title = "Rest",
        icon = R.drawable.rest,
        route = Screen.Rest.route
    ),
    NavigationItem(
        title = "Hydration",
        icon = R.drawable.watter,
        route = Screen.Hydration.route
    ),
    NavigationItem(
        title = "Setting",
        icon = R.drawable.settings,
        route = Screen.Settings.route
    )
)
