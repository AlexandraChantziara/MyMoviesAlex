package com.texnognosia.mymoviesalex

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.texnognosia.mymoviesalex.dataclasses.MovieInformation
import com.texnognosia.mymoviesalex.dataclasses.NavigationEnum
import com.texnognosia.mymoviesalex.dataclasses.SearchResults
import com.texnognosia.mymoviesalex.httpClient.getKtorClient
import com.texnognosia.mymoviesalex.theme.beige
import com.texnognosia.mymoviesalex.theme.brown1
import com.texnognosia.mymoviesalex.theme.charcoalGray
import com.texnognosia.mymoviesalex.theme.cherry
import com.texnognosia.mymoviesalex.theme.chocolate
import com.texnognosia.mymoviesalex.theme.darkGreen
import com.texnognosia.mymoviesalex.theme.darkcherry
import com.texnognosia.mymoviesalex.theme.ekrou
import com.texnognosia.mymoviesalex.theme.icyWhite
import com.texnognosia.mymoviesalex.theme.lightBeige
import com.texnognosia.mymoviesalex.theme.lightMenta
import com.texnognosia.mymoviesalex.theme.menta
import com.texnognosia.mymoviesalex.theme.oil
import com.texnognosia.mymoviesalex.theme.orange2
import com.texnognosia.mymoviesalex.theme.paleApricot
import com.texnognosia.mymoviesalex.theme.paleGreen
import com.texnognosia.mymoviesalex.theme.peachy
import com.texnognosia.mymoviesalex.theme.softGreen
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import mymoviesalex.composeapp.generated.resources.Res
import mymoviesalex.composeapp.generated.resources.movie1
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

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
    val navController = rememberNavController()
    val startDestination by remember { mutableStateOf(NavigationEnum.MOVIES) }


    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = oil, drawerContentColor = Color.White) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text("Info and more", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                    HorizontalDivider()

                    Text("Section 1", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold)
                    NavigationEnum.entries.forEach{nav ->
                        NavigationDrawerItem(
                            label = { Text(nav.title, color = Color.White) },
                            selected = false,
                            onClick = {
                                mainViewModel.currentRoute = nav.route
                                navController.navigate(nav.route)
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text("Section 2", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold)
                    NavigationDrawerItem(
                        label = { Text("Settings", color = Color.White) },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = null, tint = Color.White) },
                        badge = { Text("20") },
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Help and feedback", color = Color.White) },
                        selected = false,
                        icon = { Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = null, tint = Color.White) },
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
                                    scope.launch { drawerState.open() }
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
                                    mainViewModel.loading = true
                                    mainViewModel.isClicked = true
                                    scope.launch(Dispatchers.IO) {
                                        val client = getKtorClient()
                                        runCatching {
                                            val response = client.get("http://www.omdbapi.com/?apikey=67b070a1&s=%s".format(search.trim().replace(" ", "_")))
                                            println(response)
                                            val responseData = response.body<SearchResults>()
                                            mainViewModel.resultList.clear()
                                            mainViewModel.resultList.addAll(responseData.search)
                                            mainViewModel.isClicked = false
                                        }.getOrElse {
                                            mainViewModel.resultList.clear()
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
                                        unfocusedContainerColor = Color.White
                                    ),
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
                    NavHost(
                        navController,
                        startDestination.route,
                        modifier = Modifier.padding(paddingValues)
                    ){
                        NavigationEnum.entries.forEach{nav->
                            composable(nav.route) {
                                when(nav){
                                    NavigationEnum.MOVIES ->{
                                        MoviesScreen(mainViewModel)
                                    }

                                    NavigationEnum.FAVORITES -> {
                                        FavoritesScreen(mainViewModel)
                                    }
                                }
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

                    mainViewModel.movieInformation?.let { info ->
                        AlertDialog(
                            containerColor = softGreen,
                            confirmButton = {
                                Button(
                                    onClick = { mainViewModel.movieInformation = null },
                                    content = { Text("OK") },
                                    colors = ButtonColors(containerColor = oil, contentColor = Color.White, disabledContentColor = beige, disabledContainerColor = lightBeige)
                                )
                            },
                            title = {
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    Spacer(Modifier.width(48.dp))
                                    Text(info.title.uppercase(), textAlign = TextAlign.Center, color = Color.White, fontWeight = FontWeight.ExtraBold, modifier = Modifier.weight(1f))
                                    IconButton(
                                        onClick = {
                                            mainViewModel.upsertMyMovies(info)
                                        },
                                        content = {
                                            Icon(Icons.Outlined.FavoriteBorder,null)
                                        }
                                    )
                                }
                            },
                            onDismissRequest = { mainViewModel.movieInformation = null },
                            text = {
                                Column(Modifier.verticalScroll(rememberScrollState())) {
                                    AsyncImage(
                                        model = info.poster,
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Τίτλος:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 4.dp), color = Color.White)
                                        Text(info.title)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Ημερομηνία έκδοσης:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.released)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Διάρκεια:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.runtime)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Είδος:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.genre)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Σκηνοθέτης:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.director)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Συγγραφέας:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.writer)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Cast:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.actors)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Υπόθεση:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.plot)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Γλώσσα:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.language)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Χώρα:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.country)
                                    }
                                    Text("Bαθμολογία:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)

                                    info.ratings.forEach { r ->
                                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 16.dp)) {
                                            Text("Πηγή:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                            Text(r.source)
                                            Text("-->", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                            Text(r.value)
                                        }
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("Imbd Rating:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.imdbRating)
                                        Text("Imbd Votes:", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
                                        Text(info.imdbVotes)
                                    }
                                }
                            },
                            properties = DialogProperties(
                                dismissOnClickOutside = false
                            )
                        )


                    }
                }
            )
        }
    )
}
@Composable
fun FavoritesScreen(mainViewModel: MainViewModel){

    val list by remember { mainViewModel.myMoviesData }.collectAsStateWithLifecycle()
    Column(Modifier.padding(4.dp)) {
        ElevatedCard(
            colors = CardDefaults.elevatedCardColors(containerColor = menta),
            content = {
                Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text("#",
                        modifier = Modifier.weight(0.48f),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = paleApricot)
                    Text("FAVORITE MOVIES",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = Color.White)
                    Spacer(modifier = Modifier.weight(0.48f))
                }
            }
        )
        LazyColumn {
            itemsIndexed(list){index, movie->
                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(containerColor = lightMenta),
                    modifier = Modifier.padding(2.dp),
                    onClick = {},
                    content = {
                        Row(
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text((index+1).toString(),
                                modifier = Modifier.weight(0.48f),
                                color = paleApricot,
                                fontWeight = FontWeight.Bold)
                            Text(movie.title.uppercase(),
                                color = menta,
                                fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.weight(0.48f))
                        }
                    }
                )
            }
        }
    }


}


@Composable
fun MoviesScreen(mainViewModel : MainViewModel){
    val scope = rememberCoroutineScope()

    Column(Modifier.padding(4.dp)) {
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
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        )
        LazyColumn {
            if(mainViewModel.resultList.isEmpty() && mainViewModel.isClicked){
                item {
                    Text("ΔΕΝ ΒΡΕΘΗΚΑΝ ΤΑΙΝΙΕΣ", modifier = Modifier.fillParentMaxSize(), textAlign = TextAlign.Center, color = Color.White, fontStyle = FontStyle.Italic)
                }
            }
            itemsIndexed(mainViewModel.resultList) { index, result ->
                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
                    onClick = {
                        mainViewModel.loading = true
                        scope.launch(Dispatchers.IO) {
                            val client = getKtorClient()
                            runCatching {
                                val response = client.get("http://www.omdbapi.com/?apikey=67b070a1&i=%s".format(result.imdbID))
                                val responseData = response.body<MovieInformation>()
                               mainViewModel.movieInformation = responseData
                            }
                            client.close()
                            mainViewModel.loading = false
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
                                color = brown1,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(result.year,
                                modifier = Modifier.weight(0.3f),
                                color = softGreen,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(result.imdbID,
                                modifier = Modifier.weight(0.2f),
                                color = softGreen,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(result.type,
                                modifier = Modifier.weight(0.2f),
                                color = softGreen,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic

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
}
