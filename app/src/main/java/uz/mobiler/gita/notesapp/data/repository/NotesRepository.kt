package uz.mobiler.gita.notesapp.data.repository

import android.content.Context
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import uz.mobiler.gita.notesapp.app.App
import uz.mobiler.gita.notesapp.data.di.FirebaseModule
import uz.mobiler.gita.notesapp.data.models.NoteModel
import java.util.UUID

class NotesRepository(private val db: FirebaseFirestore = FirebaseModule.firestore) {
    private val noteCollection = db.collection("notes")
    suspend fun addNote(noteModel: NoteModel): Result<Unit> {
        return runCatching {
            val id = UUID.randomUUID().toString()
            val note = NoteModel(
                id,
                noteModel.title,
                noteModel.body,
                System.currentTimeMillis(),
                FirebaseAuth.getInstance().currentUser?.uid.toString()
            )
            noteCollection.document(id).set(note).await()
        }
    }

    suspend fun getNotes(): Result<List<NoteModel>> {
        return runCatching {
            noteCollection
                .whereEqualTo(
                    "userId",
                    FirebaseAuth.getInstance().currentUser?.uid.toString()
                )
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(NoteModel::class.java) }
                .sortedByDescending { it.time }
        }
    }

    fun observeNotesByFlow(): Flow<List<NoteModel>> = callbackFlow {
        val listener = noteCollection
            .whereEqualTo(
                "userId",
                FirebaseAuth.getInstance().currentUser?.uid.toString()
            )
            .addSnapshotListener { snapshots, exception ->
                val list = snapshots?.documents?.mapNotNull {
                    it.toObject(NoteModel::class.java)
                }.orEmpty().sortedByDescending { it.time }
                trySend(list.reversed())
            }

        awaitClose { listener.remove() }
    }

    suspend fun updateNotes(noteModel: NoteModel): Result<Unit> {
        return runCatching {
            val doc = Firebase.firestore.collection("notes").document(noteModel.id)
            doc.update("title", noteModel.title, "body", noteModel.body).await()
        }
    }

    suspend fun deleteNotes(noteModel: NoteModel): Result<Unit> {
        return runCatching {
            noteCollection.document(noteModel.id).delete().await()
        }
    }
}