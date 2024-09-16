package com.mangotestworkchat.app

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangotestworkchat.app.data.UserDataToken
import com.mangotestworkchat.app.repository.Repository
import com.mangotestworkchat.app.utils.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

open class ViewModelBase @Inject constructor(
    private val repository: Repository,
    private val sharedPref: SharedPref
): ViewModel() {

    fun showToastToUser (context: Context, message: String, length: Int = Toast.LENGTH_SHORT) {
        viewModelScope.launch(Dispatchers.Main) {
            Toast.makeText(context, message, length).show()
        }
    }
    fun saveUserDataTokenVM(context: Context, data: UserDataToken) {
        sharedPref.saveAccessToken(context, data.accessToken)
        repository.saveUserDataToken(data)
    }

    fun getSaveTokenVM(context: Context) : String? {
        return sharedPref.getAccessToken(context)
    }
}