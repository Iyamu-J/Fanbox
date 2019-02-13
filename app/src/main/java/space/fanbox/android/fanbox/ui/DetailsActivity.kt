package space.fanbox.android.fanbox.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import space.fanbox.android.fanbox.R
import space.fanbox.android.fanbox.database.LettersDatabase
import space.fanbox.android.fanbox.databinding.ActivityDetailsBinding
import space.fanbox.android.fanbox.viewmodel.LetterDetailsViewModel
import space.fanbox.android.fanbox.viewmodel.LetterDetailsViewModelFactory

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var letterId: String
    private lateinit var db: LettersDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra(getString(R.string.extra_letter_id))) {
            letterId = intent.getStringExtra(getString(R.string.extra_letter_id))

            binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
            setSupportActionBar(binding.toolbar)

            db = LettersDatabase.getInstance(this)

            val viewModel = ViewModelProviders.of(this, LetterDetailsViewModelFactory(db, letterId))
                .get(LetterDetailsViewModel::class.java)

            binding.letter = viewModel.getLetter()

            title = viewModel.getLetter().subject
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
