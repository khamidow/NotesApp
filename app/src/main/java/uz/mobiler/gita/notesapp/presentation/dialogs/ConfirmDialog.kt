package uz.mobiler.gita.notesapp.presentation.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import uz.mobiler.gita.notesapp.R
import uz.mobiler.gita.notesapp.data.models.NoteModel

class ConfirmDialog(private val model: NoteModel) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.confirm_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            300.dpToPx(this),
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = true

        val info: TextView = view.findViewById(R.id.info_tv)
        info.text =
            "Confirm deletion: ${if (model.title.length > 7) model.title.take(7) + "..." else model.title}?"

        val noBtn: AppCompatButton = view.findViewById(R.id.no_btn)
        noBtn.setOnClickListener {
            dismiss()
        }
        val yesBtn: AppCompatButton = view.findViewById(R.id.yes_btn)
        yesBtn.setOnClickListener {
            parentFragmentManager.setFragmentResult("ConfirmDialog", bundleOf("delete" to true))
//            viewModel.deleteNotes(model)
            dismiss()
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    fun Number.dpToPx(context: ConfirmDialog): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}