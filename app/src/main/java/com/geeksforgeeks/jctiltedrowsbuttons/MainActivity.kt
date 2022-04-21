package com.geeksforgeeks.jctiltedrowsbuttons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.tan

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // Creating a Simple Scaffold Layout for the application
            Scaffold(

                // Creating a Top Bar
                topBar = { TopAppBar(title = { Text("GFG | Tilted Rows with Buttons", color = Color.White) }, backgroundColor = Color(0xff0f9d58)) },

                // Creating Content
                content = {

                    // Creating a Column Layout
                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                        val border = BorderStroke(1.dp, Color.Black)
                        val shape = ParallelogramShape(45f)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(10.dp)
                                .border(border, shape = shape)
                        ) {
                            var counter by remember { mutableStateOf(10) }
                            TextButton(
                                onClick = { counter -= 1 },
                                shape = shape,
                                border = border,
                                modifier = Modifier
                                    .height(45.dp)
                                    .width(100.dp)
                            ) {
                                Text("-")
                            }
                            Text(counter.toString())
                            TextButton(
                                onClick = { counter += 1 },
                                shape = shape,
                                border = border,
                                modifier = Modifier
                                    .height(45.dp)
                                    .width(100.dp)
                            ) {
                                Text("+")
                            }
                        }

                    }
                }
            )
        }
    }
}

class ParallelogramShape(private val angle: Float): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ) = Outline.Generic(
        Path().apply {
            val width = size.width - size.height / tan(angle)
            moveTo(size.width - width, 0f)
            lineTo(size.width, 0f)
            lineTo(width, size.height)
            lineTo(0f, size.height)
            lineTo(size.width - width, 0f)
        }
    )
}
