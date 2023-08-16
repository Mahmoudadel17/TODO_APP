package com.example.todo.commonComponents


import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun OptionMenu(showMenu:Boolean,onShowClick:()->Unit,onDismissClick:()->Unit,onItemSelect:(Int) -> Unit) {

    val context = LocalContext.current

    TopAppBar(
        title = { Text("My AppBar") },
        actions = {

            IconButton(onClick = { onShowClick()}) {
                Icon(Icons.Default.MoreVert, "")
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { onDismissClick() }
            ) {

                DropdownMenuItem(onClick = {
                    onItemSelect(1)
                    Toast.makeText(context, "Update Task", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Update Task")
                }
                DropdownMenuItem(onClick = {
                    onItemSelect(2)
                    Toast.makeText(context, "Delete Task", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Delete Task")
                }
                DropdownMenuItem(onClick = {
                    onItemSelect(3)
                    Toast.makeText(context, "Add to favorite", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Add to favorite")
                }

            }


        }
    )
}