package space.fanbox.android.fanbox.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import space.fanbox.android.fanbox.R
import space.fanbox.android.fanbox.databinding.LetterItemBinding
import space.fanbox.android.fanbox.model.Letter
import space.fanbox.android.fanbox.listenerbindings.Presenter
import space.fanbox.android.fanbox.viewmodel.LetterViewModel

class LetterListAdapter : RecyclerView.Adapter<LetterListAdapter.ViewHolder>() {

    private lateinit var letterList: List<Letter>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LetterItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.letter_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(letterList[position])
    }

    override fun getItemCount(): Int {
        return if (::letterList.isInitialized) letterList.size else 0
    }

    fun setLetterList(letterList: List<Letter>) {
        this.letterList = letterList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: LetterItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = LetterViewModel()
        private val presenter = Presenter(binding.root.context)

        fun bind(letter: Letter) {
            viewModel.bind(letter)
            viewModel.id = letter.id
            binding.viewModel = viewModel
            binding.presenter = presenter
        }
    }
}