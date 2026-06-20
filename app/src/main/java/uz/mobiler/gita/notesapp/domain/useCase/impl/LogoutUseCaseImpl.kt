package uz.mobiler.gita.notesapp.domain.useCase.impl

import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.repository.AuthRepository
import uz.mobiler.gita.notesapp.domain.useCase.LoginUseCase
import uz.mobiler.gita.notesapp.domain.useCase.LogoutUseCase
import uz.mobiler.gita.notesapp.domain.useCase.RegisterUseCase

class LogoutUseCaseImpl(val rep: AuthRepository) : LogoutUseCase {
    override suspend fun invoke(){
        rep.logOut()
    }
}