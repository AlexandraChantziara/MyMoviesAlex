package com.texnognosia.mymoviesalex

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.texnognosia.mymoviesalex.theme.beige
import com.texnognosia.mymoviesalex.theme.charcoalGray
import com.texnognosia.mymoviesalex.theme.lightBeige
import com.texnognosia.mymoviesalex.theme.oil
import com.texnognosia.mymoviesalex.theme.orange2
import com.texnognosia.mymoviesalex.theme.paleApricot
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import mymoviesalex.composeapp.generated.resources.Res
import mymoviesalex.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
   MyMoviesAlex()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMoviesAlex() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = lightBeige,
        topBar = {
            CenterAlignedTopAppBar(
                colors =TopAppBarDefaults.topAppBarColors(containerColor = oil),
                title = { Text("Movies", color = Color.White, fontSize = 25.sp, fontStyle =FontStyle.Italic, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(8.dp))},
                navigationIcon = {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(contentColor =  Color.White),
                        onClick = {

                        },
                        content = {
                            Icon(Icons.Default.Menu, null)
                        }
                    )
                },
                actions = {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(contentColor = paleApricot),
                        onClick = {

                        },
                        content = {
                            Icon(Icons.Default.Download, null)
                        }
                    )
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(contentColor = lightBeige),
                        onClick = {

                        },
                        content = {
                            Icon(Icons.Default.Search, null)
                        }
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(Modifier.padding(paddingValues).padding(4.dp)) {
                ElevatedCard(
                    content = {
                        Row(
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        ) {
                            Text("#",
                                modifier = Modifier.weight(0.05f).padding(),
                                color = charcoalGray,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text("ΕΙΚΟΝΑ",
                                modifier = Modifier.weight(0.1f).padding(),
                                color = orange2,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text("ΤΙΤΛΟΣ",
                                modifier = Modifier.weight(0.4f).padding(),
                                color = orange2,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text("ΚΑΤΗΓΟΡΙΑ",
                                modifier = Modifier.weight(0.3f).padding(),
                                color = orange2,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text("ΔΙΑΡΚΕΙΑ",
                                modifier = Modifier.weight(0.1f).padding(),
                                color = orange2,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                )
            }

        }
    )
}