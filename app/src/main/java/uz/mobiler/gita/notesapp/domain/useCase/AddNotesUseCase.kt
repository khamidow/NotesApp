package uz.mobiler.gita.notesapp.domain.useCase

import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.models.NoteModel

interface AddNotesUseCase {
    suspend operator fun invoke(noteModel: NoteModel): Result<Unit>
}