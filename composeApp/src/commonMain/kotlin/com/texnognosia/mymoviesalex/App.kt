package com.texnognosia.mymoviesalex

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.texnognosia.mymoviesalex.dataclasses.MovieInformation
import com.texnognosia.mymoviesalex.dataclasses.SearchResults
import com.texnognosia.mymoviesalex.httpClient.getKtorClient
import com.texnognosia.mymoviesalex.theme.beige
import com.texnognosia.mymoviesalex.theme.brown1
import com.texnognosia.mymoviesalex.theme.charcoalGray
import com.texnognosia.mymoviesalex.theme.cherry
import com.texnognosia.mymoviesalex.theme.chocolate
import com.texnognosia.mymoviesalex.theme.darkcherry
import com.texnognosia.mymoviesalex.theme.icyWhite
import com.texnognosia.mymoviesalex.theme.lightBeige
import com.texnognosia.mymoviesalex.theme.menta
import com.texnognosia.mymoviesalex.theme.oil
import com.texnognosia.mymoviesalex.theme.orange2
import com.texnognosia.mymoviesalex.theme.paleApricot
import com.texnognosia.mymoviesalex.theme.paleGreen
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mymoviesalex.composeapp.generated.resources.Res
import mymoviesalex.composeapp.generated.resources.movie1
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.slf4j.helpers.NOP_FallbackServiceProvider

@Composable
@Preview
fun App() {
   MyMoviesAlex()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMoviesAlex(mainViewModel: MainViewModel = koinViewModel()) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showSearch by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text("Info and ", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                    HorizontalDivider()

                    Text("Section 1", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    NavigationDrawerItem(
                        label = { Text("Item 1") },
                        selected = false,
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Item 2") },
                        selected = false,
                        onClick = { /* Handle click */ }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text("Section 2", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    NavigationDrawerItem(
                        label = { Text("Settings") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                        badge = { Text("20") }, // Placeholder
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Help and feedback") },
                        selected = false,
                        icon = { Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = null) },
                        onClick = { /* Handle click */ },
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState,
        content = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = lightBeige,
                topBar = {
                    CenterAlignedTopAppBar(
                        colors =TopAppBarDefaults.topAppBarColors(containerColor = oil),
                        title = {
                            Row {
                            Text("Movies", color = Color.White, fontSize = 25.sp, fontStyle =FontStyle.Italic, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(8.dp))
                            Image(painter= painterResource(Res.drawable.movie1), null, modifier = Modifier.height(40.dp))
                        }
                        },
                        navigationIcon = {
                            IconButton(
                                colors = IconButtonDefaults.iconButtonColors(contentColor =  Color.White),
                                onClick = {
                                    scope.launch {
                                        if (drawerState.isClosed) {
                                            drawerState.open()
                                        } else {
                                            drawerState.close()
                                        }
                                    }
                                },
                                content = {
                                    Icon(Icons.Default.Menu, null)
                                },
                                enabled = search.isNotBlank()
                            )
                        },
                        actions = {
                            IconButton(
                                colors = IconButtonDefaults.iconButtonColors(contentColor = paleApricot),
                                onClick = {
                                    mainViewModel.loading = true
                                    scope.launch(Dispatchers.IO) {
                                        val client = getKtorClient()
                                        runCatching {
                                            val response = client.get("http://www.omdbapi.com/?apikey=67b070a1&s=%s".format(search.trim().replace(" ", "_")))
                                            println(response)
                                            val responseData = response.body<SearchResults>()
                                            mainViewModel.resultList.clear()
                                            mainViewModel.resultList.addAll(responseData.search)
                                        }
                                        mainViewModel.loading = false
                                        client.close()
                                    }
                                },
                                content = {
                                    Icon(Icons.Default.Download, null)
                                }
                            )
                            if (showSearch) {
                                OutlinedTextField(
                                    value = search,
                                    onValueChange = {
                                        search = it
                                    },
                                    textStyle = TextStyle(fontStyle = FontStyle.Italic),
                                    colors =  OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = darkcherry,
                                        unfocusedBorderColor = beige,
                                        cursorColor = paleApricot,
                                        focusedTextColor = darkcherry,
                                        unfocusedTextColor = beige,
                                        focusedLabelColor = darkcherry,
                                        unfocusedLabelColor = beige,
                                        focusedLeadingIconColor = oil,
                                        unfocusedLeadingIconColor = beige,
                                        focusedTrailingIconColor = oil,
                                        unfocusedTrailingIconColor = beige,
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White)
                                    ,
                                    label = {
                                        Text("Αναζήτηση")
                                    },
                                    leadingIcon = {
                                        IconButton(
                                            onClick = {
                                                showSearch = false
                                                search = ""
                                            },
                                            content = {
                                                Icon(Icons.Filled.SearchOff, null)
                                            }
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(
                                            onClick = { search = "" }
                                        ){
                                            Icon(Icons.Filled.Close, null)
                                        }
                                    }
                                )
                            } else {

                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(contentColor = lightBeige),
                                    onClick = {
                                        showSearch = true
                                    },
                                    content = {
                                        Icon(Icons.Default.Search, null)
                                    }
                                )
                            }
                        }
                    )
                },
                content = { paddingValues ->
                    Column(Modifier.padding(paddingValues).padding(4.dp)) {
                        ElevatedCard(
                            colors = CardDefaults.elevatedCardColors(containerColor = brown1),
                            content = {
                                Row(
                                    modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center
                                ) {
                                    Text("#",
                                        modifier = Modifier.weight(0.05f),
                                        color = charcoalGray,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                    Text("ΤΙΤΛΟΣ",
                                        modifier = Modifier.weight(0.2f),
                                        color = icyWhite,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text("ΧΡΟΝΟΛΟΓΙΑ",
                                        modifier = Modifier.weight(0.3f),
                                        color = icyWhite,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text("imbdID",
                                        modifier = Modifier.weight(0.2f),
                                        color = icyWhite,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text("ΕΙΔΟΣ",
                                        modifier = Modifier.weight(0.2f),
                                        color = icyWhite,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text("ΕΙΚΟΝΑ",
                                        modifier = Modifier.weight(0.1f),
                                        color = icyWhite,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold)
                                }
                            }
                        )
                        LazyColumn(
                        ) {
                            itemsIndexed(mainViewModel.resultList) { index, result ->
                                ElevatedCard(
                                    colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
                                    onClick = {
                                        mainViewModel.loading = true
                                        scope.launch(Dispatchers.IO) {
                                            scope.launch(Dispatchers.IO) {
                                                val client = getKtorClient()
                                                runCatching {
                                                    val response = client.get("http://www.omdbapi.com/?apikey=67b070a1&i=%s".format(result.imdbID))
                                                    val responseData = response.body<MovieInformation>()
                                                    println(responseData)
                                                }
                                                client.close()
                                                mainViewModel.loading = false
                                            }
                                        }
                                    },
                                    modifier = Modifier.padding(4.dp),
                                    content = {
                                        Row(
                                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text((index + 1).toString(),
                                                modifier = Modifier.weight(0.05f),
                                                color = charcoalGray,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                            Text(result.title,
                                                modifier = Modifier.weight(0.2f),
                                                color = orange2,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(result.year,
                                                modifier = Modifier.weight(0.3f),
                                                color = orange2,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(result.imdbID,
                                                modifier = Modifier.weight(0.2f),
                                                color = orange2,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(result.type,
                                                modifier = Modifier.weight(0.2f),
                                                color = orange2,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                            AsyncImage(
                                                modifier = Modifier.weight(0.1f),
                                                model = result.poster,
                                                contentDescription = null
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                    if (mainViewModel.loading) {
                        BasicAlertDialog(
                            onDismissRequest = {}
                        ) {
                            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            )
        }
    )
}
