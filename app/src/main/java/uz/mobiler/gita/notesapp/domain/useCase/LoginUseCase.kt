package uz.mobiler.gita.notesapp.domain.useCase

import uz.mobiler.gita.notesapp.data.models.AuthModel

interface LoginUseCase {
    suspend operator fun invoke(authModel: AuthModel): Result<Unit>
}