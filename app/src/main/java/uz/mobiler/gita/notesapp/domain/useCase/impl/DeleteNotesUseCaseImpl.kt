package uz.mobiler.gita.notesapp.domain.useCase.impl

import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.models.NoteModel
import uz.mobiler.gita.notesapp.data.repository.NotesRepository
import uz.mobiler.gita.notesapp.domain.useCase.AddNotesUseCase
import uz.mobiler.gita.notesapp.domain.useCase.DeleteNotesUseCase

class DeleteNotesUseCaseImpl(val rep: NotesRepository): DeleteNotesUseCase {
    override suspend fun invoke(noteModel: NoteModel): Result<Unit> {
        return rep.deleteNotes(noteModel)
    }
}