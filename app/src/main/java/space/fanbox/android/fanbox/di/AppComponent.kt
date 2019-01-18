package space.fanbox.android.fanbox.di

import dagger.Component
import space.fanbox.android.fanbox.viewmodel.LetterViewModel
import javax.inject.Singleton

@Component(modules = [RestModule::class])
@Singleton
interface AppComponent {

    fun inject(letterViewModel: LetterViewModel)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun restModule(restModule: RestModule): Builder
    }
}