package uz.mobiler.gita.notesapp.domain.useCase.impl

import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.models.NoteModel
import uz.mobiler.gita.notesapp.data.repository.AuthRepository
import uz.mobiler.gita.notesapp.data.repository.NotesRepository
import uz.mobiler.gita.notesapp.domain.useCase.GetNotesUseCase
import uz.mobiler.gita.notesapp.domain.useCase.LoginUseCase
import uz.mobiler.gita.notesapp.domain.useCase.RegisterUseCase

class GetNotesUseCaseImpl(val rep: NotesRepository): GetNotesUseCase {
    override suspend fun invoke(): Result<List<NoteModel>> {
        return rep.getNotes()
    }
}