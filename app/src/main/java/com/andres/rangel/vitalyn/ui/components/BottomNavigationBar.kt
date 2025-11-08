package com.andres.rangel.vitalyn.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andres.rangel.vitalyn.R
import com.andres.rangel.vitalyn.navigation.NavigationItem
import com.andres.rangel.vitalyn.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    horizontalInset: Dp = 20.dp,
    verticalInset: Dp = 20.dp,
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
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
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
    val scale by animateFloatAsState(
        targetValue = if (selected) 1.20f else 1f,
        animationSpec = spring(
            dampingRatio = 0.5f,
            stiffness = 300f
        ),
        label = "iconScaleAnim"
    )

    IconButton(
        onClick = onClick,
        modifier = Modifier.size(64.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = item.title,
                modifier = Modifier
                    .size(28.dp)
                    .graphicsLayer(scaleX = scale, scaleY = scale),
                tint = if (selected)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

val navigationItems = listOf(
    NavigationItem(
        title = "Sports",
        icon = R.drawable.pesa,
        route = Screen.Sports.route
    ),
    NavigationItem(
        title = "Nutrition",
        icon = R.drawable.bienestar,
        route = Screen.Nutrition.route
    ),
    NavigationItem(
        title = "Rest",
        icon = R.drawable.descanso,
        route = Screen.Rest.route
    ),
    NavigationItem(
        title = "Hydration",
        icon = R.drawable.agua,
        route = Screen.Hydration.route
    ),
    NavigationItem(
        title = "Setting",
        icon = R.drawable.ajustar,
        route = Screen.Settings.route
    )
)
