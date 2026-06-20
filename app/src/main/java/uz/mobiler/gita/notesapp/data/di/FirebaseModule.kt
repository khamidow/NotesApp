package uz.mobiler.gita.notesapp.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseModule {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
}