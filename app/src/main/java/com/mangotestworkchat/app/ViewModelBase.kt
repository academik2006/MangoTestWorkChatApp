package com.mangotestworkchat.app

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangotestworkchat.app.data.UserDataToken
import com.mangotestworkchat.app.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

open class ViewModelBase @Inject constructor(
    private val repository: Repository
): ViewModel() {

    fun showToastToUser (context: Context, message: String, length: Int = Toast.LENGTH_SHORT) {
        viewModelScope.launch(Dispatchers.Main) {
            Toast.makeText(context, message, length).show()
        }
    }
    fun saveUserDataTokenVM(data: UserDataToken) {
        repository.saveUserDataToken(data)
    }
}