package uz.mobiler.gita.notesapp.presentation.screens.login

import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.launch
import uz.mobiler.gita.notesapp.R
import uz.mobiler.gita.notesapp.data.models.AuthModel
import uz.mobiler.gita.notesapp.data.repository.AuthRepository
import uz.mobiler.gita.notesapp.domain.useCase.LoginGoogleUseCase
import uz.mobiler.gita.notesapp.domain.useCase.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val loginGoogleUseCase: LoginGoogleUseCase
) : ViewModel() {

    val loadingLiveData = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()
    val successLiveData = MutableLiveData<Boolean>()

    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()

    private var navigationHandled = false

    init {
        if (AuthRepository().isLoggedIn()) {
            successLiveData.value = true
        }
    }

    fun login(email: String, password: String) {
        if (checkData(email, password)) {
            viewModelScope.launch {
                loadingLiveData.value = true
                loginUseCase(AuthModel(email, password))
                    .onSuccess {
                        messageLiveData.value = "Login success"
                        loadingLiveData.value = false
                        successLiveData.value = true
                    }.onFailure {
                        messageLiveData.value = it.message
                        loadingLiveData.value = false
                        passwordLiveData.value = "No such user"
                        emailLiveData.value = "No such user"
                    }
            }
        }
    }

    fun loginWithGoogle(context: Context) {
        viewModelScope.launch {
            try {
                loadingLiveData.value = true
                val credentialManager = CredentialManager.create(context)
                val request = buildCredentialRequest(context)
                val result = credentialManager.getCredential(context, request = request)
                val idToken = getGoogleIdToken(result.credential)

                loginGoogleUseCase(idToken)
                    .onSuccess {
                        loadingLiveData.value = false
                        successLiveData.value = true
                    }
                    .onFailure {
                        loadingLiveData.value = false
                        messageLiveData.value = it.message ?: "Google sign-in failed"
                    }

            } catch (e: GetCredentialCancellationException) {
                loadingLiveData.value = false
            } catch (e: GetCredentialException) {
                loadingLiveData.value = false
                messageLiveData.value = e.message ?: "Google sign-in failed"
            } catch (e: Exception) {
                loadingLiveData.value = false
                messageLiveData.value = e.message ?: "Unexpected error"
            }
        }
    }

    fun onNavigationHandled() {
        navigationHandled = true
    }

    fun isNavigationPending(): Boolean = !navigationHandled

    private fun buildCredentialRequest(context: Context): GetCredentialRequest {
        val googleOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.default_web_client_id))
            .setAutoSelectEnabled(false)
            .build()

        return GetCredentialRequest.Builder()
            .addCredentialOption(googleOption)
            .build()
    }

    private fun getGoogleIdToken(credential: Credential): String {
        if (credential !is CustomCredential) {
            error("Credential type is invalid")
        }
        if (credential.type != GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            error("Unexpected credential type")
        }
        return try {
            GoogleIdTokenCredential.createFrom(credential.data).idToken
        } catch (e: Exception) {
            throw IllegalStateException("Failed to extract Google ID token")
        }
    }

    private fun checkData(email: String, password: String): Boolean {
        if (email.endsWith(".com") && email.substring(0, email.length - 4).last()
                .isLetter() && email.contains("@") && !email.contains(" ")
        ) {
            emailLiveData.value = ""
        } else {
            emailLiveData.value = "Email must end with @<domain>.com"
        }
        if (password.length >= 6 && !password.contains(" ")) {
            passwordLiveData.value = ""
        } else {
            passwordLiveData.value = "Must be at least 6 characters"
        }

        return emailLiveData.value.toString().isEmpty() && passwordLiveData.value.toString().isEmpty()
    }
}