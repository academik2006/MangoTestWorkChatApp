package com.mangotestworkchat.app.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mangotestworkchat.app.ui.theme.GreyLightRegularRoboto14
import com.mangotestworkchat.app.ui.theme.blue_APP
import com.mangotestworkchat.app.ui.theme.light_green_APP
import com.mangotestworkchat.app.ui.theme.red_APP
import com.mangotestworkchat.app.ui.theme.white_APP



@Composable
fun LoadingProgressAnimation(
    indicatorSize: Dp = 24.dp,
    circleColors: List<Color> = listOf(
        red_APP,
        blue_APP,
        light_green_APP,
        white_APP
    ),
    animationDuration: Int = 360
) {

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotateAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            )
        ), label = ""
    )

    CircularProgressIndicator(
        modifier = Modifier
            .size(size = indicatorSize)
            .rotate(degrees = rotateAnimation.value)
            .border(
                width = 4.dp,
                brush = Brush.sweepGradient(circleColors),
                shape = CircleShape
            ),
        progress = 1f,
        strokeWidth = 1.dp,
        color = MaterialTheme.colors.background
    )
}


