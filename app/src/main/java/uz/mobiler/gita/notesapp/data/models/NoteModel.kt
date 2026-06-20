package uz.mobiler.gita.notesapp.data.models

data class NoteModel(
    val id: String = "",
    val title: String = "",
    val body: String = "",
    val time: Long = 0,
    val userId: String = ""
)
