package com.denreyes.forgetfulnote

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import com.denreyes.forgetfulnote.ui.theme.ForgetfulNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForgetfulNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ForgetfulNoteApp()
                }
            }
        }
    }
}

@Composable
fun ForgetfulNoteApp() {
    var addNoteTitle by remember { mutableStateOf("") }
    var addNoteText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(8.dp)
    ) {
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = { showDialog = true }) {
            Text(text = "Add Note")
        }
        ForgetfulNoteItem()
    }

    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false },
            confirmButton = {
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = { showDialog = false }) {
                        addNoteTitle = ""
                        addNoteText = ""
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
                        value = addNoteText,
                        placeholder = { Text("Body") },
                        onValueChange = { addNoteText = it },
                        modifier = Modifier.height(100.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun ForgetfulNoteItem() {
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
            Text(text = "Title")
            Text(text = "Description")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgetfulNoteAppPreview() {
    ForgetfulNoteApp()
}