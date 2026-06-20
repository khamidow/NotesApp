package uz.mobiler.gita.notesapp.domain.useCase

import kotlinx.coroutines.flow.Flow
import uz.mobiler.gita.notesapp.data.models.NoteModel

interface ObserveNotesByFlowSnapshotUseCase {
    operator fun invoke(): Flow<List<NoteModel>>
}