package com.extaleusinc.credentialsmanager.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.extaleusinc.credentialsmanager.MAIN_COLOR
import com.extaleusinc.credentialsmanager.R
import com.extaleusinc.credentialsmanager.TEXT_COLOR
import com.extaleusinc.credentialsmanager.TOP_BAR_COLOR
import com.extaleusinc.credentialsmanager.ui.widgets.Entity
import com.extaleusinc.credentialsmanager.ui.widgets.Folder
import com.extaleusinc.credentialsmanager.ui.widgets.HorizontalDivider
import com.extaleusinc.data.model.EntityModel
import com.extaleusinc.data.model.FolderModel

@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Home2(navController = navController, state = state, onAction = viewModel::processAction)
}

@Composable
fun Home(
    navController: NavController,
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = TOP_BAR_COLOR)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Folders",
                style = TextStyle(fontSize = 24.sp, color = TEXT_COLOR)
            )
        }

        HorizontalDivider()

        if (state.foldersWithEntities.isNotEmpty()) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = MAIN_COLOR)
            ) {
                items(state.foldersWithEntities) { folderWithEntities ->
                    Folder(folderWithEntities, navController, onAction)
                    Spacer(Modifier.height(10.dp))
                }
            }
        } else {
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = MAIN_COLOR)
            )
        }

        HorizontalDivider()

        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = TOP_BAR_COLOR),
        ) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.Center)) {
                Text(text = "Add New Folder")
            }
        }
    }
}

@Composable
fun Home2(
    navController: NavController,
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    var foldersDetailsOpen by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.07f)
                .background(Color.Black.copy(alpha = 0.4f)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Home",
                style = TextStyle(fontSize = 32.sp, color = Color.White)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.3f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = if (foldersDetailsOpen) painterResource(id = R.drawable.keyboard_arrow_left_24dp)
                else painterResource(id = R.drawable.keyboard_arrow_right_24dp),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { foldersDetailsOpen = !foldersDetailsOpen }
            )
            Text(
                text = if (state.foldersWithEntities.isNotEmpty()) {
                    state.foldersWithEntities.find { it.folder.selected }?.folder?.title ?: ""
                } else "",
                style = TextStyle(color = Color.White, fontSize = 24.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
//                    .fillMaxWidth(if (foldersDetailsOpen) 0.3f else 0.1f)
                    .background(Color.Black.copy(alpha = 0.3f)),
//                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.foldersWithEntities.isNotEmpty()) {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(state.foldersWithEntities) { folderWithEntities ->
                            ShortNameComponent(
                                foldersDetailsOpen,
                                folderWithEntities.folder,
                                onAction
                            )
                            Spacer(Modifier.height(5.dp))
                        }
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.add_24dp),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { }
                )
                Image(
                    painter = painterResource(id = R.drawable.delete_24dp),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { }
                )
            }

            if (state.foldersWithEntities.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
//                        .background(Color.Black.copy(alpha = 0.1f))
                ) {
                    items(
                        state.foldersWithEntities.find { it.folder.selected }?.entities
                            ?: emptyList()
                    ) { entity ->
                        HorizontalDivider(4.dp)
                        Entity(entity, navController, onAction)
                    }
                    item {
                        HorizontalDivider(4.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun ShortNameComponent(
    foldersDetailsOpen: Boolean,
    folder: FolderModel,
    onAction: (HomeAction) -> Unit
) {
    Row(
        modifier = Modifier
            .background(if (folder.selected) Color.White.copy(alpha = 0.1f) else Color.Transparent)
            .padding(10.dp)
            .clickable {
                onAction(HomeAction.ChangeSelectedFolder(folder))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
//            painter = painterResource(id = icon),
            painter = painterResource(id = R.drawable.mail_24dp), //TODO replace with folder.icon
            contentDescription = null,
            modifier = Modifier
        )
        if (foldersDetailsOpen) {
            Text(
                text = folder.title,
                style = TextStyle(color = Color.White, fontSize = 18.sp),
                modifier = Modifier
                    .widthIn(max = 100.dp)
                    .padding(start = 10.dp),
            )
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    Home2(navController = NavController(LocalContext.current), state = HomeState(
        false, mutableListOf<FolderWithEntities>().apply {
            add(
                FolderWithEntities(
                    FolderModel(0, "Emails", "desc", false, selected = true),
                    listOf(
                        EntityModel(
                            0,
                            "title",
                            "username",
                            "password",
                            "url",
                            "desc",
                            favorite = false
                        ),
                        EntityModel(
                            0,
                            "title",
                            "username",
                            "password",
                            "url",
                            "desc",
                            false
                        ),
                        EntityModel(
                            0,
                            "title",
                            "username",
                            "password",
                            "url",
                            "desc",
                            false
                        )
                    )
                )
            )
            repeat(20) {
                add(
                    FolderWithEntities(
                        FolderModel(0, "Workspace", "desc", false),
                        listOf(
                            EntityModel(
                                0,
                                "title",
                                "username",
                                "password",
                                "url",
                                "desc",
                                favorite = false
                            ),
                            EntityModel(
                                0,
                                "title",
                                "username",
                                "password",
                                "url",
                                "desc",
                                false
                            ),
                            EntityModel(
                                0,
                                "title",
                                "username",
                                "password",
                                "url",
                                "desc",
                                false
                            )
                        )
                    )
                )
            }
        }
    ), onAction = {})
}