package uz.mobiler.gita.notesapp.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.mobiler.gita.notesapp.data.repository.AuthRepository
import uz.mobiler.gita.notesapp.domain.useCase.LoginGoogleUseCase
import uz.mobiler.gita.notesapp.domain.useCase.impl.LoginGoogleUseCaseImpl
import uz.mobiler.gita.notesapp.domain.useCase.impl.LoginUseCaseImpl
import uz.mobiler.gita.notesapp.domain.useCase.impl.RegisterUseCaseImpl
import uz.mobiler.gita.notesapp.presentation.screens.register.RegisterViewModel

class LoginViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val rep = AuthRepository()
        return LoginViewModel(LoginUseCaseImpl(rep), LoginGoogleUseCaseImpl(rep)) as T
    }
}