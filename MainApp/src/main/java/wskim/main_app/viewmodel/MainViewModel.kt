package wskim.main_app.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.main_app.repository.SearchResultRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : SearchResultRepository?
) : ViewModel(){

    private var a = 1

    private val _text :  MutableState<String> = mutableStateOf("")
    val text: State<String> = _text

    init {
        if (repository == null) {
            // fake
            _text.value = "일단 시작"
        } else {
            // normal

        }
    }

    fun textChange() {
        _text.value = "일단 시작" + System.currentTimeMillis()
    }

    fun onClick() {
        a++
        _text.value = "일단 시작 $a"
    }
}