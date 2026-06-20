package uz.mobiler.gita.notesapp.domain.useCase.impl

import uz.mobiler.gita.notesapp.data.repository.AuthRepository
import uz.mobiler.gita.notesapp.domain.useCase.LoginGoogleUseCase

class LoginGoogleUseCaseImpl(val rep: AuthRepository): LoginGoogleUseCase {
    override suspend fun invoke(idToken:String): Result<Unit> {
        return rep.loginWithGoogle(idToken)
    }
}