package com.nguyen.jetnote

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nguyen.jetnote.model.Note
import com.nguyen.jetnote.screen.NoteScreen
import com.nguyen.jetnote.ui.theme.JetNoteTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    val notes = remember { mutableStateListOf<Note>() }
                    NoteScreen(
                        modifier = Modifier.padding(padding),
                        notes = notes,
                        onAddNote = { notes.add(it) },
                        onRemoveNote = { notes.remove(it) })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNoteTheme {
    }
}