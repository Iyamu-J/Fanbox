package space.fanbox.android.fanbox.viewmodel

import androidx.lifecycle.MutableLiveData
import space.fanbox.android.fanbox.di.BaseViewModel
import space.fanbox.android.fanbox.model.Letter

class LetterViewModel : BaseViewModel() {

    private val senderName = MutableLiveData<String>()
    private val dateCreated = MutableLiveData<String>()
    private val recipient = MutableLiveData<String>()
    private val subject = MutableLiveData<String>()
    private val body = MutableLiveData<String>()
    private val love = MutableLiveData<String>()
    private val hate = MutableLiveData<String>()
    private val categories = MutableLiveData<List<String>>()

    fun bind(letter: Letter) {
        senderName.value = letter.sender_name
        dateCreated.value = letter.date_created
        recipient.value = letter.recipient
        subject.value = letter.subject
        body.value = letter.body
        love.value = letter.love
        hate.value = letter.hate
        categories.value = letter.category
    }

    fun getSenderName(): MutableLiveData<String> {
        return senderName
    }

    fun getDateCreated(): MutableLiveData<String> {
        return dateCreated
    }

    fun getRecipient(): MutableLiveData<String> {
        return recipient
    }

    fun getSubject(): MutableLiveData<String> {
        return subject
    }

    fun getBody(): MutableLiveData<String> {
        return body
    }

    fun getLove(): MutableLiveData<String> {
        return love
    }

    fun getHate(): MutableLiveData<String> {
        return hate
    }

    fun getCategories(): MutableLiveData<List<String>> {
        return categories
    }
}