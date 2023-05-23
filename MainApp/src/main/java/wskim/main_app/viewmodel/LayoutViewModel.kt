package wskim.main_app.viewmodel

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.main_app.page_list.layout.vo.LayoutListVO
import wskim.main_app.repository.SearchResultRepository
import javax.inject.Inject

@HiltViewModel
class LayoutViewModel @Inject constructor(
    private val repository : SearchResultRepository?
) : ViewModel(){

    val layoutList : SnapshotStateList<LayoutListVO> = mutableStateListOf()

    init {
        if (repository == null) {
            // fake
            layoutList.addAll(
                arrayListOf<LayoutListVO>(
                    LayoutListVO(
                        0,
                        "컴포넌트 예시) 가",
                        0
                    ),
                    LayoutListVO(
                        0,
                        "컴포넌트 예시) 나",
                        0
                    ),
                    LayoutListVO(
                        0,
                        "컴포넌트 예시) 다",
                        0
                    ),
                )
            )
        } else {
            // normal
            layoutList.addAll(initLayoutList())
        }
    }

    private fun initLayoutList() : ArrayList<LayoutListVO> {
        return arrayListOf<LayoutListVO>().apply {
            add(
                LayoutListVO(
                    0,
                    "constraint layout",
                    0
                )
            )
            add(
                LayoutListVO(
                1,
                "고민 중",
                0
                )
            )
        }
    }

    fun itemClick(position: Int) {
        layoutList[position] = layoutList[position].apply {
            viewCount++
        }
    }
}