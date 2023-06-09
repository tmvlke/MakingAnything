package wskim.main_app.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.data.repository.SearchResultRepository
import javax.inject.Inject

@HiltViewModel
class ComponentViewModel @Inject constructor(
    private val repository : SearchResultRepository?
) : ViewModel(){

    val componentList : SnapshotStateList<String> = mutableStateListOf()

    private val _id :  MutableState<Int> = mutableStateOf(0)
    val id: State<Int> = _id

    private val _name :  MutableState<String> = mutableStateOf("")
    val name: State<String> = _name

    init {
        if (repository == null) {
            // fake
            componentList.addAll(
                arrayListOf<String>().apply {
                    add("컴포넌트 예시) 가")
                    add("컴포넌트 예시) 나")
                    add("컴포넌트 예시) 다")
                    add("컴포넌트 예시) 라")
                    add("컴포넌트 예시) 마")
                }
            )
        } else {
            // normal
            componentList.addAll(initComponentList())
        }

    }

    private fun initComponentList() : ArrayList<String> {
        return arrayListOf<String>().apply {
            add("constraint layout")
            add("2")
            add("3")
            add("4")
            add("5")
        }
    }

    fun inputId(id: Int) {
        _id.value = id
    }

    fun inputName(name: String) {
        _name.value = name
    }

    fun itemClick(position: Int) {
        componentList[position] = "${System.currentTimeMillis()}"
    }
}