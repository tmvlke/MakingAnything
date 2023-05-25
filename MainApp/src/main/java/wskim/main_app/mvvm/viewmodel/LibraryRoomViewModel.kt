package wskim.main_app.mvvm.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import wskim.main_app.core.navigation.dto.LibraryDetailDTO
import wskim.main_app.mvvm.repository.LibraryRoomRepository
import wskim.main_app.page_list.library.vo.LibraryListVO
import javax.inject.Inject

@HiltViewModel
class LibraryRoomViewModel @Inject constructor(
    private val repository: LibraryRoomRepository?
) : ViewModel(){

    val libraryList : SnapshotStateList<LibraryListVO> = mutableStateListOf()

    init {
        if (repository == null) {
            // fake
            libraryList.addAll(
                arrayListOf(
                    LibraryListVO(text = "컴포넌트 예시) 가"),
                    LibraryListVO(text = "컴포넌트 예시) 나"),
                    LibraryListVO(text = "컴포넌트 예시) 다"),
                )
            )
        } else {
            // normal
            libraryList.addAll(initLibraryList())

            viewModelScope.launch {
                repository.selectDummyData(1)
            }
        }

    }

    private fun initLibraryList() : ArrayList<LibraryListVO> {
        return arrayListOf(
            LibraryListVO(text = "Room", screen = LibraryDetailDTO.Screen.Room),
        ).apply {
            this.forEachIndexed { index, layoutListVO ->
                layoutListVO.index = index
            }

            // default
            add(
                LibraryListVO(
                    index = -1,
                    text = "고민 중",
                )
            )
        }
    }

    fun itemClick(position: Int) {
        libraryList[position] = libraryList[position].apply {
            viewCount++
        }
    }
}