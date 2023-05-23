package wskim.main_app.viewmodel

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.baselibrary.proguard_safe_zone.network.vo.KakaoIntegrationContentListVO
import wskim.baselibrary.proguard_safe_zone.network.vo.KakaoIntegrationContentVO
import wskim.main_app.repository.SearchResultRepository
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import kotlin.streams.toList

@HiltViewModel
class EtcViewModel @Inject constructor(
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