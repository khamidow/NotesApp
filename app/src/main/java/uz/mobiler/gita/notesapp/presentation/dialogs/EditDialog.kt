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
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import uz.mobiler.gita.notesapp.R
import uz.mobiler.gita.notesapp.data.models.NoteModel

class EditDialog(private val model: NoteModel) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            310.dpToPx(this),
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = true


        val title: EditText = view.findViewById(R.id.ed_title)
        title.setText(model.title)
        val body: EditText = view.findViewById(R.id.ed_body)
        body.setText(model.body)

        title.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                title.setBackgroundResource(R.drawable.ed_bcg_selected)
            } else {
                title.setBackgroundResource(R.drawable.ed_bcg)
            }
        }
        body.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                body.setBackgroundResource(R.drawable.ed_bcg_selected)
            } else {
                body.setBackgroundResource(R.drawable.ed_bcg)
            }
        }

        val saveBtn: AppCompatButton = view.findViewById(R.id.save_btn)
        saveBtn.text = "Update"
        saveBtn.setOnClickListener {
            if (title.text.toString().trim().isEmpty() || body.text.toString().trim()
                    .isEmpty()
            ) {
                if (title.text.toString().trim().isEmpty())
                    title.setBackgroundResource(R.drawable.ed_bcg_wrong)
                if (body.text.toString().trim().isEmpty())
                    body.setBackgroundResource(R.drawable.ed_bcg_wrong)
            } else {
                parentFragmentManager.setFragmentResult("EditDialog", bundleOf("title" to title.text.toString().trim(),"body" to body.text.toString().trim()))
                dismiss()
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    fun Number.dpToPx(context: EditDialog): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}