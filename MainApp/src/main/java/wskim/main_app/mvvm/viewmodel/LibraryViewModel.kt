package wskim.main_app.mvvm.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.main_app.core.navigation.dto.LibraryDetailDTO
import wskim.main_app.mvvm.repository.SearchResultRepository
import wskim.main_app.page_list.library.vo.LibraryListVO
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: SearchResultRepository?
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
        }

    }

    private fun initLibraryList() : ArrayList<LibraryListVO> {
        return arrayListOf(
            LibraryListVO(text = "Room", screen = LibraryDetailDTO.Screen.Room),
            LibraryListVO(text = "Lottie", screen = LibraryDetailDTO.Screen.Lottie),
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