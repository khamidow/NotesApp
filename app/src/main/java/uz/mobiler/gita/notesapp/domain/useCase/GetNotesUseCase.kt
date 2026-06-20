package uz.mobiler.gita.notesapp.domain.useCase

import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.models.NoteModel

interface GetNotesUseCase {
    suspend operator fun invoke(): Result<List<NoteModel>>
}