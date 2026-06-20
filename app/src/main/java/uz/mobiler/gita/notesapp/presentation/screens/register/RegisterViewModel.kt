package uz.mobiler.gita.notesapp.presentation.screens.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.models.NoteModel
import uz.mobiler.gita.notesapp.data.repository.AuthRepository
import uz.mobiler.gita.notesapp.domain.useCase.RegisterUseCase

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {

    val loadingLiveData = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()
    val successLiveData = MutableLiveData<Boolean>()

    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val confirmLiveData = MutableLiveData<String>()

    fun register(email: String, password: String, confirm: String) {
        if (checkData(email, password, confirm)) {
            viewModelScope.launch {
                loadingLiveData.value = true
                registerUseCase(AuthModel(email, password))
                    .onSuccess {
                        messageLiveData.value = "Register success"
                        loadingLiveData.value = false
                        successLiveData.value = true
                    }.onFailure {
                        messageLiveData.value = it.message
                        loadingLiveData.value = false
                        emailLiveData.value = "Used email"
                    }
            }
        }
    }

    private fun checkData(email: String, password: String, confirm: String): Boolean {
        if (email.endsWith(".com") && email.substring(0, email.length - 4).last()
                .isLetter() && email.contains("@") && !email.contains(" ")
        ) {
            emailLiveData.value = ""
        } else {
            emailLiveData.value = "Email must end with @<domain>.com"
        }
        if (password == confirm) {
            if (password.length >= 6 && !password.contains(" ")) {
                passwordLiveData.value = ""
            } else {
                passwordLiveData.value = "Must be at least 6 characters"
            }
            if (confirm.length >= 6 && !confirm.contains(" ")) {
                confirmLiveData.value = ""
            } else {
                confirmLiveData.value = "Must be at least 6 characters"
            }
        } else {
            passwordLiveData.value = "Passwords don't match"
            confirmLiveData.value = "Passwords don't match"
        }
        return emailLiveData.value.toString().isEmpty() && passwordLiveData.value.toString().isEmpty() && confirmLiveData.value.toString().isEmpty()
    }
}