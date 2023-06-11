package wskim.main_app.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wskim.domain.ui.UiRoot
import wskim.domain.usecase.LayoutListUseCase
import wskim.main_app.core.navigation.dto.LayoutDetailDTO
import wskim.main_app.page_list.layout.vo.LayoutListVO
import javax.inject.Inject

@HiltViewModel
class LayoutViewModel @Inject constructor(
    private val mainListUseCase: LayoutListUseCase?,
) : ViewModel() {

    val layoutList : SnapshotStateList<LayoutListVO> = mutableStateListOf()

    init {
        if (mainListUseCase == null) {
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
            viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    mainListUseCase.selectSpecificTabViewCount(UiRoot.MainTab.Layout)
                }

                val result = initLayoutList().asFlow().map { data1 ->
                    response?.find { data2 ->
                        // 데이터를 비교하여 같은 데이터를 찾습니다
                        data1.index == data2.index
                    }?.let { matchedData ->
                        // 같은 데이터가 있는 경우 편집을 수행합니다
                        data1.copy(viewCount = matchedData.count)
                    } ?: data1
                }.toList()

                layoutList.addAll(result)
            }
        }
    }

    private fun initLayoutList() : ArrayList<LayoutListVO> {
        return arrayListOf(
            LayoutListVO(text = "ConstraintLayout", screen = LayoutDetailDTO.Screen.ConstraintLayout),
            LayoutListVO(text = "리스트 무한 스크롤(paging3)", screen = LayoutDetailDTO.Screen.ListInfinityScrollPaging3),
            LayoutListVO(text = "CoordinatorLayout", screen = LayoutDetailDTO.Screen.CoordinatorLayout),
            LayoutListVO(text = "SlidingPopUp", screen = LayoutDetailDTO.Screen.SlidingPopUp),
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
        viewModelScope.launch {
            mainListUseCase?.insertViewCount(uiRoot = UiRoot.MainTab.Layout, position = position)

            layoutList[position] = layoutList[position].apply {
                viewCount++
            }
        }
    }
}