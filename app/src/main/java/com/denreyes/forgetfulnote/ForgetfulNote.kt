package com.denreyes.forgetfulnote

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class NoteItem(val id: Int,
                    var title: String,
                    var body: String,
                    var isEditing: Boolean = false)

@Composable
fun ForgetfulNoteApp() {
    var sItems by remember { mutableStateOf(listOf<NoteItem>()) }
    var addNoteTitle by remember { mutableStateOf("") }
    var addNoteBody by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(8.dp)
    ) {
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = { showDialog = true }) {
            Text(text = "Add Note")
        }
        LazyColumn() {
            items(sItems) {
                item -> ForgetfulNoteItem(item,
                    onEditClick = {
                         sItems = sItems.map{it.copy(isEditing = it.id == item.id)}
                    },
                    onDeleteClick = {
                         sItems = sItems - item;
                    }
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false },
            confirmButton = {
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = {
                        if(addNoteTitle.isNotBlank() && addNoteBody.isNotBlank()) {
                            val newNote = NoteItem(
                                id = sItems.size + 1,
                                addNoteTitle,
                                addNoteBody
                            )
                            sItems = sItems + newNote
                            addNoteTitle = ""
                            addNoteBody = ""
                            showDialog = false
                        }
                    }) {
                        Text(text = "Add")
                    }
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                }
            },
            title = { Text(text = "Add Note") },
            text = {
                Column {
                    OutlinedTextField(
                        value = addNoteTitle,
                        placeholder = { Text("Title") },
                        onValueChange = { addNoteTitle = it },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = addNoteBody,
                        placeholder = { Text("Body") },
                        onValueChange = { addNoteBody = it },
                        modifier = Modifier.height(100.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun ForgetfulNoteItem(
    item: NoteItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .border(
            border = BorderStroke(2.dp, Color.Gray),
            shape = RoundedCornerShape(16)
        ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.7f)) {
            Text(text = item.title)
            Text(text = item.body)
        }
        IconButton(onClick = onEditClick) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
        IconButton(onClick = onDeleteClick) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgetfulNoteAppPreview() {
    ForgetfulNoteApp()
}