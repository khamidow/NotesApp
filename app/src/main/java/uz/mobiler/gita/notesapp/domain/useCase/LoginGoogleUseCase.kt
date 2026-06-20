package uz.mobiler.gita.notesapp.domain.useCase

import uz.mobiler.gita.notesapp.data.models.AuthModel

interface LoginGoogleUseCase {
    suspend operator fun invoke(idToken: String): Result<Unit>
}