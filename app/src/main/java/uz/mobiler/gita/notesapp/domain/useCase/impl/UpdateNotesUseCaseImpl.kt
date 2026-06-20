package uz.mobiler.gita.notesapp.domain.useCase.impl

import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.models.NoteModel
import uz.mobiler.gita.notesapp.data.repository.NotesRepository
import uz.mobiler.gita.notesapp.domain.useCase.AddNotesUseCase
import uz.mobiler.gita.notesapp.domain.useCase.UpdateNotesUseCase

class UpdateNotesUseCaseImpl(val rep: NotesRepository): UpdateNotesUseCase {
    override suspend fun invoke(noteModel: NoteModel): Result<Unit> {
        return rep.updateNotes(noteModel)
    }
}