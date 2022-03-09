package com.example.myapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NoteViewModel: ViewModel() {
    var notes = mutableStateOf(mutableListOf<Note>())

    fun addNote(note: Note) {
        var newNotes = notes.value.toMutableList()
        newNotes.add(note)
        notes.value = newNotes
    }
}