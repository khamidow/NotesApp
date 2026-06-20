package uz.mobiler.gita.notesapp.domain.useCase.impl

import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.repository.AuthRepository
import uz.mobiler.gita.notesapp.domain.useCase.RegisterUseCase

class RegisterUseCaseImpl(val rep: AuthRepository): RegisterUseCase {
    override suspend fun invoke(authModel: AuthModel): Result<Unit> {
        return rep.register(authModel)
    }
}