package my.dreamtech.trackstockapp.presentation.track_stock

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assistant
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import my.dreamtech.trackstockapp.R
import my.dreamtech.trackstockapp.destinations.AddEditStockDetailScreenDestination
import my.dreamtech.trackstockapp.destinations.AppContentDestination
import my.dreamtech.trackstockapp.destinations.CompanyListingsScreenDestination
import my.dreamtech.trackstockapp.ui.theme.DEFAULT_PADDING
import my.dreamtech.trackstockapp.ui.theme.DarkBlue

import kotlin.math.PI
import kotlin.math.sin

@RequiresApi(Build.VERSION_CODES.S)
private fun getRenderEffect(): RenderEffect {
    val blurEffect = RenderEffect
        .createBlurEffect(80f, 80f, Shader.TileMode.MIRROR)

    val alphaMatrix = RenderEffect.createColorFilterEffect(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f, 0f,
                    0f, 0f, 0f, 50f, -5000f
                )
            )
        )
    )

    return RenderEffect
        .createChainEffect(alphaMatrix, blurEffect)
}

@Composable
@Destination(start = true)
fun MainScreen(
    navigator: DestinationsNavigator
) {
    val isMenuExtended = remember { mutableStateOf(false) }

    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        ), label = ""
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
        ), label = ""
    )

    val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getRenderEffect().asComposeRenderEffect()
    } else {
        null
    }

    MainScreen(
        navigator = navigator,
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress,
        clickAnimationProgress = clickAnimationProgress
    ) {
        isMenuExtended.value = isMenuExtended.value.not()
    }
}

@Composable
fun MainScreen(navigator: DestinationsNavigator,

    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { },

) {


    val scope : CoroutineScope = rememberCoroutineScope()
    val scaffoldState : ScaffoldState = rememberScaffoldState()


    Scaffold (
        topBar = {
            TopAppBar(title ={
                Text(text = "Your Stock Details")
            }, navigationIcon = {
                 IconButton(onClick = {
                     scope.launch {
                         scaffoldState.drawerState.open()
                     }
                 }) {
                     Icon(painter = painterResource(id = R.drawable.baseline_dehaze_24), contentDescription = "Menu")
                 }
            })
        }, scaffoldState = scaffoldState,
        drawerContent = {}
    ){

        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
                .background(DarkBlue),

            contentAlignment = Alignment.BottomCenter

        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                val viewModel: TrackViewModel = hiltViewModel()
                val stockList = viewModel.getAllStocks.collectAsState(initial = listOf())
                LazyColumn {
                    items(stockList.value) { stock ->
                        StockItem(
                            stock = stock, modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                                .clickable {
                                    val id = stock.id
                                    navigator.navigate(AddEditStockDetailScreenDestination(id))
                                },
                            viewModel = viewModel
                        )
                    }
                }

            }
            Circle(
                color = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                animationProgress = 0.5f
            )

            FabGroup(
                navigator = navigator,
                renderEffect = renderEffect,
                animationProgress = fabAnimationProgress
            )
            FabGroup(
                navigator = navigator,
                renderEffect = null,
                animationProgress = fabAnimationProgress,
                toggleAnimation = toggleAnimation
            )
            Circle(
                color = Color.White,
                animationProgress = clickAnimationProgress
            )

        }
    }
}

@Composable
fun Circle(color: Color, animationProgress: Float) {
    val animationValue = sin(PI * animationProgress).toFloat()

    Box(
        modifier = Modifier
            .padding(DEFAULT_PADDING.dp)
            .size(56.dp)
            .scale(2 - animationValue)
            .border(
                width = 2.dp,
                color = color.copy(alpha = color.alpha * animationValue),
                shape = CircleShape
            )
    )
}



@Composable
fun FabGroup(navigator: DestinationsNavigator,
    animationProgress: Float = 0f,
    renderEffect: androidx.compose.ui.graphics.RenderEffect? = null,
    toggleAnimation: () -> Unit = { }
) {
    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer { this.renderEffect = renderEffect }
            .padding(bottom = DEFAULT_PADDING.dp),
        contentAlignment = Alignment.BottomCenter
    ) {


        AnimatedFab(
            icon = Icons.Default.BarChart,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 72.dp,
                        end = 210.dp
                    ) * FastOutSlowInEasing.transform(0f, 0.8f, animationProgress)
                ),
            opacity = LinearEasing.transform(0.2f, 0.7f, animationProgress)
            , onClick = {
                  navigator.navigate(
                      CompanyListingsScreenDestination()
                  )
            }
        )

        AnimatedFab(
            icon = Icons.Default.Assistant,
            modifier = Modifier.padding(
                PaddingValues(
                    bottom = 88.dp,
                ) * FastOutSlowInEasing.transform(0.1f, 0.9f, animationProgress)
            ),
            opacity = LinearEasing.transform(0.3f, 0.8f, animationProgress),
            onClick = {
                navigator.navigate(
                    AppContentDestination()
                )
            }
        )

        AnimatedFab(
            icon = Icons.Default.Add,
            modifier = Modifier.padding(
                PaddingValues(
                    bottom = 72.dp,
                    start = 210.dp
                ) * FastOutSlowInEasing.transform(0.2f, 1.0f, animationProgress)
            ),
            opacity = LinearEasing.transform(0.4f, 0.9f, animationProgress),
            onClick = {
                navigator.navigate(AddEditStockDetailScreenDestination(id = 0L))
            }
        )

        AnimatedFab(
            modifier = Modifier
                .scale(1f - LinearEasing.transform(0.5f, 0.85f, animationProgress)),
        )

        AnimatedFab(
            icon = Icons.Default.Add,
            modifier = Modifier
                .rotate(
                    225 * FastOutSlowInEasing
                        .transform(0.35f, 0.65f, animationProgress)
                ),
            onClick = toggleAnimation,
            backgroundColor = Color.Transparent
        )
    }
}

@Composable
fun AnimatedFab(
    modifier: Modifier,
    icon: ImageVector? = null,
    opacity: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
        backgroundColor = backgroundColor,
        modifier = modifier.scale(1.25f)
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = Color.White.copy(alpha = opacity)
            )
        }
    }
}



