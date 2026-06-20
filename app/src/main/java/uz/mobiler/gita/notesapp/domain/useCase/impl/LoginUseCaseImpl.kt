package uz.mobiler.gita.notesapp.domain.useCase.impl

import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.repository.AuthRepository
import uz.mobiler.gita.notesapp.domain.useCase.LoginUseCase
import uz.mobiler.gita.notesapp.domain.useCase.RegisterUseCase

class LoginUseCaseImpl(val rep: AuthRepository): LoginUseCase {
    override suspend fun invoke(authModel: AuthModel): Result<Unit> {
        return rep.login(authModel)
    }
}