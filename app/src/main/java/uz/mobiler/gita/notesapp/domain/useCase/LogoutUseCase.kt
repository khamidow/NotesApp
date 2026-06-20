package uz.mobiler.gita.notesapp.domain.useCase

import uz.mobiler.gita.notesapp.data.models.AuthModel

interface LogoutUseCase {
    suspend operator fun invoke()
}