package uz.mobiler.gita.notesapp.presentation.screens.main

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.androidbroadcast.vbpd.viewBinding
import uz.mobiler.gita.notesapp.R
import uz.mobiler.gita.notesapp.data.models.NoteModel
import uz.mobiler.gita.notesapp.databinding.ScreenMainBinding
import uz.mobiler.gita.notesapp.presentation.adapter.NotesAdapter
import uz.mobiler.gita.notesapp.presentation.dialogs.AddDialog
import uz.mobiler.gita.notesapp.presentation.dialogs.ConfirmDialog
import uz.mobiler.gita.notesapp.presentation.dialogs.ConfirmLogOutDialog
import uz.mobiler.gita.notesapp.presentation.dialogs.EditDialog

class MainScreen : Fragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel by viewModels<MainViewModel> { MainViewModelFactory() }
    private val adapter = NotesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setDeleteClickListener { model, i ->
            parentFragmentManager.setFragmentResultListener(
                "ConfirmDialog",
                viewLifecycleOwner
            ) { _, bundle ->
                val result = bundle.getBoolean("delete")
                if (result) {
                    viewModel.deleteNotes(model)
                }
            }

            val dialogFragment = ConfirmDialog(model)
            dialogFragment.show(parentFragmentManager, "CustomDialogTag")

//            val dialog = Dialog(requireContext())
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setCancelable(true)
//            dialog.setContentView(R.layout.confirm_dialog)
//
//            dialog.window?.setLayout(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//
//            val info: TextView = dialog.findViewById(R.id.info_tv)
//            info.text =
//                "Do you want to delete ${if (model.title.length > 7) model.title.take(5) + "..." else model.title}?"
//
//            val noBtn: AppCompatButton = dialog.findViewById(R.id.no_btn)
//            noBtn.setOnClickListener {
//                dialog.dismiss()
//            }
//            val yesBtn: AppCompatButton = dialog.findViewById(R.id.yes_btn)
//            yesBtn.setOnClickListener {
//                viewModel.deleteNotes(model)
//                dialog.dismiss()
//            }
//            dialog.show()
        }

        adapter.setEditClickListener { model, i ->
            parentFragmentManager.setFragmentResultListener(
                "EditDialog",
                viewLifecycleOwner
            ) { _, bundle ->
                val title = bundle.getString("title")
                val body = bundle.getString("body")
                if (title!=null && body!=null) {
                    viewModel.updateNote(model.copy(title = title, body = body))
                }
            }

            val dialogFragment = EditDialog(model)
            dialogFragment.show(parentFragmentManager, "CustomDialogTag")

//            val dialog = Dialog(requireContext())
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setCancelable(true)
//            dialog.setContentView(R.layout.add_dialog)
//
//            dialog.window?.setLayout(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//
//            val title: EditText = dialog.findViewById(R.id.ed_title)
//            title.setText(model.title)
//            val body: EditText = dialog.findViewById(R.id.ed_body)
//            body.setText(model.body)
//
//            title.setOnFocusChangeListener { _, hasFocus ->
//                if (hasFocus) {
//                    title.setBackgroundResource(R.drawable.ed_bcg_selected)
//                } else {
//                    title.setBackgroundResource(R.drawable.ed_bcg)
//                }
//            }
//            body.setOnFocusChangeListener { _, hasFocus ->
//                if (hasFocus) {
//                    body.setBackgroundResource(R.drawable.ed_bcg_selected)
//                } else {
//                    body.setBackgroundResource(R.drawable.ed_bcg)
//                }
//            }
//
//            val saveBtn: AppCompatButton = dialog.findViewById(R.id.save_btn)
//            saveBtn.text = "Update"
//            saveBtn.setOnClickListener {
//                if (title.text.toString().trim().isEmpty() || body.text.toString().trim()
//                        .isEmpty()
//                ) {
//                    if (title.text.toString().trim().isEmpty())
//                        title.setBackgroundResource(R.drawable.ed_bcg_wrong)
//                    if (body.text.toString().trim().isEmpty())
//                        body.setBackgroundResource(R.drawable.ed_bcg_wrong)
//                } else {
//                    viewModel.updateNote(
//                        model.copy(
//                            title = title.text.toString().trim(),
//                            body = body.text.toString().trim()
//                        )
//                    )
//                    dialog.dismiss()
//                }
//            }
//            dialog.show()
        }

        binding.addBtn.setOnClickListener {

            parentFragmentManager.setFragmentResultListener(
                "AddDialog",
                viewLifecycleOwner
            ) { _, bundle ->
                val title = bundle.getString("title")
                val body = bundle.getString("body")
                if (title!=null && body!=null) {
                    viewModel.addNote(NoteModel(title = title, body = body))
                }
            }

            val dialogFragment = AddDialog()
            dialogFragment.show(parentFragmentManager, "CustomDialogTag")

//            val dialog = Dialog(requireContext())
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setCancelable(true)
//            dialog.setContentView(R.layout.add_dialog)
//
//            dialog.window?.setLayout(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//
//            val title: EditText = dialog.findViewById(R.id.ed_title)
//            val body: EditText = dialog.findViewById(R.id.ed_body)
//
//            title.setOnFocusChangeListener { _, hasFocus ->
//                if (hasFocus) {
//                    title.setBackgroundResource(R.drawable.ed_bcg_selected)
//                } else {
//                    title.setBackgroundResource(R.drawable.ed_bcg)
//                }
//            }
//            body.setOnFocusChangeListener { _, hasFocus ->
//                if (hasFocus) {
//                    body.setBackgroundResource(R.drawable.ed_bcg_selected)
//                } else {
//                    body.setBackgroundResource(R.drawable.ed_bcg)
//                }
//            }
//
//            val saveBtn: AppCompatButton = dialog.findViewById(R.id.save_btn)
//            saveBtn.setOnClickListener {
//                if (title.text.toString().trim().isEmpty() || body.text.toString().trim()
//                        .isEmpty()
//                ) {
//                    if (title.text.toString().trim().isEmpty())
//                        title.setBackgroundResource(R.drawable.ed_bcg_wrong)
//                    if (body.text.toString().trim().isEmpty())
//                        body.setBackgroundResource(R.drawable.ed_bcg_wrong)
//                } else {
//                    viewModel.addNote(
//                        NoteModel(
//                            title = title.text.toString().trim(),
//                            body = body.text.toString().trim()
//                        )
//                    )
//                    dialog.dismiss()
//                }
//            }
//            dialog.show()
        }

        binding.logOutBtn.setOnClickListener {

            parentFragmentManager.setFragmentResultListener(
                "LogOutDialog",
                viewLifecycleOwner
            ) { _, bundle ->
                val result = bundle.getBoolean("logOut")
                if (result) {
                    viewModel.logOut()
                }
            }

            val dialogFragment = ConfirmLogOutDialog()
            dialogFragment.show(parentFragmentManager, "CustomDialogTag")

//            val dialog = Dialog(requireContext())
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setCancelable(true)
//            dialog.setContentView(R.layout.confirm_dialog)
//
//            dialog.window?.setLayout(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//
//            val noBtn: AppCompatButton = dialog.findViewById(R.id.no_btn)
//            noBtn.setOnClickListener {
//                dialog.dismiss()
//            }
//            val yesBtn: AppCompatButton = dialog.findViewById(R.id.yes_btn)
//            yesBtn.setOnClickListener {
//                viewModel.logOut()
//                dialog.dismiss()
//            }
//            dialog.show()
        }

        binding.noteRv.adapter = adapter

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.loadingPb.visibility = View.VISIBLE
            } else {
                binding.loadingPb.visibility = View.GONE
            }
        }

        viewModel.messageLiveData.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
                binding.placeholderTv.visibility = View.GONE
                binding.placeholderImg.visibility = View.GONE
            } else {
                adapter.submitList(it)
                binding.placeholderTv.visibility = View.VISIBLE
                binding.placeholderImg.visibility = View.VISIBLE
            }
        }

        viewModel.logOutLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_mainScreen_to_loginScreen)
        }
    }

}