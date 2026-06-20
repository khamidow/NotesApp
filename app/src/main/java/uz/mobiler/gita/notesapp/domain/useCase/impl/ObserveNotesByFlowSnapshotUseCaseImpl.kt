package uz.mobiler.gita.notesapp.domain.useCase.impl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.mobiler.gita.notesapp.app.App
import uz.mobiler.gita.notesapp.data.models.NoteModel
import uz.mobiler.gita.notesapp.data.repository.NotesRepository
import uz.mobiler.gita.notesapp.domain.useCase.ObserveNotesByFlowSnapshotUseCase

class ObserveNotesByFlowSnapshotUseCaseImpl(private val rep: NotesRepository) :
    ObserveNotesByFlowSnapshotUseCase {
    override fun invoke(): Flow<List<NoteModel>> {
        return rep.observeNotesByFlow()
    }
}