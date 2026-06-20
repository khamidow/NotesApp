package uz.mobiler.gita.notesapp.domain.useCase.impl

import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.models.NoteModel
import uz.mobiler.gita.notesapp.data.repository.NotesRepository
import uz.mobiler.gita.notesapp.domain.useCase.AddNotesUseCase

class AddNotesUseCaseImpl(val rep: NotesRepository): AddNotesUseCase {
    override suspend fun invoke(noteModel: NoteModel): Result<Unit> {
        return rep.addNote(noteModel)
    }
}