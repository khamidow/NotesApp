package uz.mobiler.gita.notesapp.presentation.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mobiler.gita.notesapp.R
import uz.mobiler.gita.notesapp.data.models.NoteModel
import uz.mobiler.gita.notesapp.databinding.ItemNoteBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    private var list = mutableListOf<NoteModel>()
    fun submitList(newList: List<NoteModel>) {
        this.list.clear()
        this.list.addAll(newList)
        notifyDataSetChanged()
    }

    private var onEditClick: ((NoteModel, Int) -> Unit)? = null
    fun setEditClickListener(f: (NoteModel, Int) -> Unit) {
        onEditClick = f
    }

    private var onDeleteClick: ((NoteModel, Int) -> Unit)? = null
    fun setDeleteClickListener(f: (NoteModel, Int) -> Unit) {
        onDeleteClick = f
    }

    inner class NotesHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteModel) {
            binding.titleTv.text = note.title
            binding.bodyTv.text = note.body
            binding.bodyTv.maxLines = note.body.length / 37 + 1

            binding.root.setOnClickListener {
                onEditClick?.invoke(
                    list[absoluteAdapterPosition],
                    absoluteAdapterPosition
                )
            }

            binding.root.setOnLongClickListener {
                binding.root.setBackgroundResource(R.drawable.ed_bcg_wrong)
                Handler(Looper.getMainLooper()).postDelayed(
                    { binding.root.setBackgroundResource(R.drawable.note_bcg) },
                    999
                )
                onDeleteClick?.invoke(list[absoluteAdapterPosition], absoluteAdapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotesHolder(ItemNoteBinding.bind(view))
    }

    override fun onBindViewHolder(
        holder: NotesHolder,
        position: Int
    ) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

}