package uz.mobiler.gita.notesapp.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import uz.mobiler.gita.notesapp.data.di.FirebaseModule
import uz.mobiler.gita.notesapp.data.models.AuthModel

class AuthRepository(private val auth: FirebaseAuth = FirebaseModule.auth) {
    suspend fun register(authModel: AuthModel): Result<Unit> {
        return runCatching {
            auth.createUserWithEmailAndPassword(authModel.email, authModel.password).await()
        }
    }

    suspend fun loginWithGoogle(idToken:String): Result<Unit>{
        return runCatching {
            require(idToken.isNotBlank()){"Token is empty!"}
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            Log.d("TTT", "loginWithGoogle-user: ${result.user}")
            Log.d("TTT", "loginWithGoogle-additional info: ${result.additionalUserInfo}")
            Unit
        }
    }

    suspend fun login(authModel: AuthModel): Result<Unit> {
        return runCatching {
            auth.signInWithEmailAndPassword(authModel.email, authModel.password).await()
        }
    }

    suspend fun logOut() {
        runCatching {
            auth.signOut()
        }
    }

    fun currentUserId(): String? = auth.currentUser?.uid
    fun isLoggedIn(): Boolean = auth.currentUser != null
}