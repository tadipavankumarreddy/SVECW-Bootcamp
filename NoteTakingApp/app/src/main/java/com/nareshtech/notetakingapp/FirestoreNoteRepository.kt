package com.nareshtech.notetakingapp

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.nareshtech.notetakingapp.room.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirestoreNoteRepository{
    private val db = Firebase.firestore

    private val notesCollection = db.collection("notes")

    private val note = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = note.asStateFlow()

    init {
        notesCollection.addSnapshotListener {snapshot, error ->
            if(error!=null) return@addSnapshotListener
            val notes = snapshot?.toObjects(Note::class.java)?.sortedByDescending { it.id }
            note.value = notes ?:emptyList()
        }
    }

    fun syncNoteToFirestore(notes: List<Note>) {
        notes.forEach { note->
            val noteMap = mapOf(
                "id" to note.id,
                "title" to note.title,
                "content" to note.content
            )

            db.collection("notes")
                .document(note.id.toString())
                .set(noteMap)
        }
    }
}