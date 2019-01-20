package space.fanbox.android.fanbox.rest

import io.reactivex.Observable
import retrofit2.http.GET
import space.fanbox.android.fanbox.model.Letter

interface WebService {

    @GET("letters/")
    fun getLetters(): Observable<List<Letter>>
}