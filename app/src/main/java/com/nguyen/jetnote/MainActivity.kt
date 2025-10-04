package com.nguyen.jetnote

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nguyen.jetnote.screen.NoteScreen
import com.nguyen.jetnote.screen.NoteViewModel
import com.nguyen.jetnote.ui.theme.JetNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    val viewModel: NoteViewModel by viewModels()
                    NoteApp(padding, viewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteApp(padding: PaddingValues, viewModel: NoteViewModel) {
    val notes = viewModel.notes.collectAsState().value

    NoteScreen(
        modifier = Modifier.padding(padding),
        notes = notes,
        onAddNote = { viewModel.addNote(it) },
        onRemoveNote = { viewModel.removeNote(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNoteTheme {
    }
}