package wskim.main_app.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LayoutTextFieldViewModel @Inject constructor() : ViewModel() {

    val inputTextValue = MutableStateFlow("")
    val resultTextValue = MutableStateFlow("")

    fun onButtonClick() {
        resultTextValue.value = inputTextValue.value
    }
}