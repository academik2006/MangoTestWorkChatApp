package com.mangotestworkchat.app.ui.registration

import android.content.Context
import com.mangotestworkchat.app.ViewModelBase
import javax.inject.Inject

class RegistrationViewModel @Inject constructor() : ViewModelBase() {

    //можно вынести эти проверки в отдельный класс, но я делать не стал.
    // Проект не большой, смысла на это время тратить нет.

    fun isDataUserValidVM(context: Context, name: String, userName: String): Boolean {
        return if (name.isEmpty() || userName.isEmpty()) {
            showToastToUser("Поле c данными не может быть пустым", context)
            false
        } else{
            if (isContainsSpecialCharacters(name) || isContainsSpecialCharacters(userName)) {
                showToastToUser("Одно из полей содержит специальные сивмолы", context)
                false
            } else true
        }
    }
}
fun isContainsSpecialCharacters(text: String): Boolean {
    return text.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
}

