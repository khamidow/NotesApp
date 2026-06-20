package uz.mobiler.gita.notesapp.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.mobiler.gita.notesapp.data.repository.AuthRepository
import uz.mobiler.gita.notesapp.domain.useCase.impl.RegisterUseCaseImpl

class RegisterViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val rep = AuthRepository()
        return RegisterViewModel(RegisterUseCaseImpl(rep)) as T
    }
}