package space.fanbox.android.fanbox.di

import androidx.lifecycle.ViewModel
import space.fanbox.android.fanbox.viewmodel.LetterViewModel

abstract class BaseViewModel: ViewModel() {

    private val appComponent: AppComponent = DaggerAppComponent
        .builder()
        .restModule(RestModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is LetterViewModel -> appComponent.inject(this)
        }
    }
}