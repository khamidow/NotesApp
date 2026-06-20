package uz.mobiler.gita.notesapp.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.mobiler.gita.notesapp.data.repository.AuthRepository
import uz.mobiler.gita.notesapp.data.repository.NotesRepository
import uz.mobiler.gita.notesapp.domain.useCase.impl.AddNotesUseCaseImpl
import uz.mobiler.gita.notesapp.domain.useCase.impl.DeleteNotesUseCaseImpl
import uz.mobiler.gita.notesapp.domain.useCase.impl.GetNotesUseCaseImpl
import uz.mobiler.gita.notesapp.domain.useCase.impl.LogoutUseCaseImpl
import uz.mobiler.gita.notesapp.domain.useCase.impl.ObserveNotesByFlowSnapshotUseCaseImpl
import uz.mobiler.gita.notesapp.domain.useCase.impl.UpdateNotesUseCaseImpl

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val rep = AuthRepository()
        val note = NotesRepository()
        val add = AddNotesUseCaseImpl(note)
        val get = GetNotesUseCaseImpl(note)
        val logOut = LogoutUseCaseImpl(rep)
        val update = UpdateNotesUseCaseImpl(note)
        val delete = DeleteNotesUseCaseImpl(note)
        val observeSnapshot = ObserveNotesByFlowSnapshotUseCaseImpl(note)
        return MainViewModel(add, get, update,delete, logOut,observeSnapshot) as T
    }
}