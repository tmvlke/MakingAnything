package wskim.main_app.mvvm.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.main_app.core.navigation.dto.LayoutDetailDTO
import wskim.main_app.mvvm.repository.SearchResultRepository
import wskim.main_app.page_list.layout.vo.LayoutListVO
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
                arrayListOf(
                    LayoutListVO(text = "컴포넌트 예시) 가"),
                    LayoutListVO(text = "컴포넌트 예시) 나"),
                    LayoutListVO(text = "컴포넌트 예시) 다"),
                )
            )
        } else {
            // normal
            layoutList.addAll(initLayoutList())
        }
    }

    private fun initLayoutList() : ArrayList<LayoutListVO> {
        return arrayListOf(
            LayoutListVO(text = "ConstraintLayout", screen = LayoutDetailDTO.Screen.ConstraintLayout),
            LayoutListVO(text = "리스트 무한 스크롤(paging3)", screen = LayoutDetailDTO.Screen.ListInfinityScrollPaging3),
            LayoutListVO(text = "CoordinatorLayout", screen = LayoutDetailDTO.Screen.CoordinatorLayout),
        ).apply {
            this.forEachIndexed { index, layoutListVO ->
                layoutListVO.index = index
            }

            // default
            add(
                LayoutListVO(
                    index = -1,
                    text = "고민 중",
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