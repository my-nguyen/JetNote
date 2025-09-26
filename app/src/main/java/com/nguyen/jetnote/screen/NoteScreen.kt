package com.nguyen.jetnote.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nguyen.jetnote.R
import com.nguyen.jetnote.components.NoteButton
import com.nguyen.jetnote.components.NoteInputText
import com.nguyen.jetnote.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var titleState by remember { mutableStateOf("") }
    var descriptionState by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = { Text(text = stringResource(R.string.app_name)) },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon",
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFFDADFE3))
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                value = titleState,
                label = "Title",
                onValueChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) titleState = it
                })
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                value = descriptionState,
                label = "Add a note",
                onValueChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) descriptionState =
                        it
                })
            NoteButton(text = "Save", onClick = {
                if (titleState.isNotEmpty() && descriptionState.isNotEmpty()) {
                    titleState = ""
                    descriptionState = ""
                }
            })
        }
    }
}

@Preview
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = emptyList(), onAddNote = {}, onRemoveNote = {})
}