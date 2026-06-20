package uz.mobiler.gita.notesapp.presentation.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.mobiler.gita.notesapp.data.models.NoteModel
import uz.mobiler.gita.notesapp.domain.useCase.AddNotesUseCase
import uz.mobiler.gita.notesapp.domain.useCase.DeleteNotesUseCase
import uz.mobiler.gita.notesapp.domain.useCase.GetNotesUseCase
import uz.mobiler.gita.notesapp.domain.useCase.LogoutUseCase
import uz.mobiler.gita.notesapp.domain.useCase.ObserveNotesByFlowSnapshotUseCase
import uz.mobiler.gita.notesapp.domain.useCase.UpdateNotesUseCase

class MainViewModel(
    private val addUseCase: AddNotesUseCase,
    private val getUseCase: GetNotesUseCase,
    private val updateUseCase: UpdateNotesUseCase,
    private val deleteUseCase: DeleteNotesUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val observeSnapshotUseCase: ObserveNotesByFlowSnapshotUseCase
) : ViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()
    val notesLiveData = MutableLiveData<List<NoteModel>>()
    val logOutLiveData = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            updateNotes()
        }
    }

    fun addNote(noteModel: NoteModel) {
        viewModelScope.launch {
            loadingLiveData.value = true
            addUseCase(noteModel).onSuccess {
                messageLiveData.value = "Note added successfully"
//                updateNotes()
                loadingLiveData.value = false
            }.onFailure {
                messageLiveData.value = it.message
                loadingLiveData.value = false
            }
        }
    }

    fun updateNote(noteModel: NoteModel) {
        viewModelScope.launch {
            loadingLiveData.value = true
            updateUseCase(noteModel).onSuccess {
                messageLiveData.value = "Note updated successfully"
//                updateNotes()
                loadingLiveData.value = false
            }.onFailure {
                messageLiveData.value = it.message
                loadingLiveData.value = false
            }
        }
    }

    fun deleteNotes(noteModel: NoteModel) {
        viewModelScope.launch {
            loadingLiveData.value = true
            deleteUseCase(noteModel).onSuccess {
                messageLiveData.value = "Note deleted successfully"
//                updateNotes()
                loadingLiveData.value = false
            }.onFailure {
                messageLiveData.value = it.message
                loadingLiveData.value = false
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            logoutUseCase()
            logOutLiveData.value = true
        }
    }

    private fun updateNotes() {
//        getUseCase().onSuccess {
//            notesLiveData.value = it
//        }.onFailure {
//            messageLiveData.value = it.message
//        }
//        viewModelScope.launch {
//            observeSnapshotUseCase().collect {
//                notesLiveData.value = it
//            }
//        }

        observeSnapshotUseCase().onEach {
            notesLiveData.value = it
        }.launchIn(viewModelScope)


    }
}