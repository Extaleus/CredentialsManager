//package com.extaleusinc.credentialsmanager.ui.widgets
//
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.rotate
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.platform.LocalClipboardManager
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.AnnotatedString
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.extaleusinc.credentialsmanager.EntityDetails
//import com.extaleusinc.credentialsmanager.R
//import com.extaleusinc.credentialsmanager.TOP_BAR_COLOR
//import com.extaleusinc.credentialsmanager.TOP_BAR_SECOND_COLOR
//import com.extaleusinc.credentialsmanager.feature.home.HomeAction
//import com.extaleusinc.data.model.EntityModel
//
//@Composable
//fun Entity(entity: EntityModel, navController: NavController, onAction: (HomeAction) -> Unit) {
//    var entityDetailsOpen by remember { mutableStateOf(true) }
//    var showDeletePopup by remember { mutableStateOf(false) }
//    val clipboardManager = LocalClipboardManager.current
//    val context = LocalContext.current
//
//    if (showDeletePopup) {
//        AlertDialog(
//            "Delete Entity",
//            "Are you sure you want to delete this entity?",
//            "Yes(Delete)",
//            "No(Cancel)",
//            onDismiss = { showDeletePopup = false },
//            onSave = {
//                onAction(HomeAction.DeleteEntity(entity))
//                showDeletePopup = false
//            })
//    }
//
//    Column(
//        Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 10.dp)
//            .clip(RoundedCornerShape(10.dp))
//            .background(TOP_BAR_COLOR)
//    ) {
//        Row(
//            Modifier
//                .fillMaxWidth()
//                .clickable { entityDetailsOpen = !entityDetailsOpen },
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(
//                Modifier
//                    .weight(1f)
//                    .padding(10.dp)
//            ) {
//                Text(entity.entityName, style = TextStyle(fontSize = 24.sp))
//                Text(entity.username)
//            }
//            Image(
//                painter = if (entityDetailsOpen) painterResource(id = R.drawable.keyboard_arrow_down_24dp)
//                else painterResource(id = R.drawable.keyboard_arrow_left_24dp),
//                contentDescription = null,
//                colorFilter = ColorFilter.tint(color = Color.Black),
//                modifier = Modifier
//                    .size(width = 32.dp, height = 32.dp)
//            )
//        }
//        if (entityDetailsOpen) {
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .background(TOP_BAR_SECOND_COLOR),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Row(
//                    Modifier
//                        .weight(1f)
//                        .fillMaxWidth()
//                        .background(TOP_BAR_SECOND_COLOR),
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.delete_24dp),
//                        contentDescription = null,
//                        colorFilter = ColorFilter.tint(color = Color.Black),
//                        modifier = Modifier
//                            .padding(top = 10.dp, bottom = 10.dp)
//                            .size(width = 32.dp, height = 32.dp)
//                            .clickable {
//                                Log.d("my", "click to delete entity")
//                                showDeletePopup = true
//                            }
//                    )
//                    Image(
//                        painter = painterResource(id = R.drawable.open_in_new_24dp),
//                        contentDescription = null,
//                        colorFilter = ColorFilter.tint(color = Color.Black),
//                        modifier = Modifier
//                            .padding(top = 10.dp, bottom = 10.dp)
//                            .size(width = 32.dp, height = 32.dp)
//                            .clickable {
//                                Log.d("my", "click to change entity")
//                                navController.navigate(
//                                    EntityDetails(
//                                        entityName = entity.entityName,
//                                        username = entity.username,
//                                        password = entity.password,
//                                        url = entity.url,
//                                        notes = entity.notes
//                                    )
//                                )
//                            }
//                    )
//                }
//                Image(
//                    painter = painterResource(id = R.drawable.vertical_line_24dp),
//                    colorFilter = ColorFilter.tint(color = TOP_BAR_COLOR),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .rotate(90f)
//                        .size(width = 32.dp, height = 32.dp)
//                )
//                Row(
//                    Modifier
//                        .weight(1f)
//                        .fillMaxWidth()
//                        .background(TOP_BAR_SECOND_COLOR),
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.mail_24dp),
//                        contentDescription = null,
//                        colorFilter = ColorFilter.tint(color = Color.Black),
//                        modifier = Modifier
//                            .padding(top = 10.dp, bottom = 10.dp)
//                            .size(width = 32.dp, height = 32.dp)
//                            .clickable {
//                                Log.d("my", "click to copy username")
//                                clipboardManager.setText(annotatedString = AnnotatedString(entity.username))
//                                Toast
//                                    .makeText(
//                                        context,
//                                        "Username copied to clipboard!",
//                                        Toast.LENGTH_SHORT
//                                    )
//                                    .show()
//                            }
//                    )
//                    Image(
//                        painter = painterResource(id = R.drawable.key_24dp),
//                        contentDescription = null,
//                        colorFilter = ColorFilter.tint(color = Color.Black),
//                        modifier = Modifier
//                            .padding(top = 10.dp, bottom = 10.dp)
//                            .size(width = 32.dp, height = 32.dp)
//                            .clickable {
//                                Log.d("my", "click to copy password")
//                                clipboardManager.setText(annotatedString = AnnotatedString(entity.password))
//                                Toast
//                                    .makeText(
//                                        context,
//                                        "Password copied to clipboard!",
//                                        Toast.LENGTH_SHORT
//                                    )
//                                    .show()
//                            }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun EntityPreview() {
//    Entity(
//        EntityModel(
//            "steam account",
//            "username",
//            "password",
//            "url",
//            "notes"
//        ),
//        NavController(LocalContext.current),
//        {}
//    )
//}
