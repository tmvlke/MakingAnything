package wskim.main_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wskim.data.repository.LayoutExampleInfinityScrollRepository
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LayoutExampleInfinityScrollViewModel @Inject constructor(
    private val repository : LayoutExampleInfinityScrollRepository?
) : ViewModel(){

    val _items = MutableStateFlow<ArrayList<String>>(arrayListOf())

    private val pagingConfig = PagingConfig(
        pageSize = 20, // 한 번에 로드할 페이지의 크기를 지정합니다. 로드하는 데이터의 개수를 의미하며, 보통 한 페이지에 표시할 아이템 수로 설정됩니다.
//        initialLoadSize = 20, // 초기 로드에 사용되는 페이지의 크기를 지정합니다. 보통 첫 번째 페이지에 로드할 아이템 수로 설정되며, pageSize보다 작을 수 있습니다
        prefetchDistance = 5, // 현재 로드된 데이터의 끝에서 추가 데이터를 사전에 로드할 거리를 지정합니다. 사용자가 스크롤할 때 미리 데이터를 로드하여 부드러운 스크롤 및 빠른 응답성을 제공할 수 있습니다.
//        enablePlaceholders = true, // 페이징 데이터를 로드할 때 아이템 자리 표시자를 사용할지 여부를 지정합니다. 자리 표시자는 실제 데이터가 로드되기 전에 임시로 표시되는 아이템입니다. false로 설정하면 로드된 데이터만 표시됩니다.
//        maxSize = 60 // 캐시로 유지할 최대 페이지 수를 지정합니다. 이 속성을 설정하면 이전에 로드한 페이지가 메모리에 유지되어 다시 로드하지 않아도 되므로 성능 향상을 가져올 수 있습니다.
    )

    private val pager = Pager(
        config = pagingConfig,
        pagingSourceFactory = { DummyDataSource() }
    )

    val pagingItems: Flow<PagingData<String>> = pager.flow.cachedIn(viewModelScope)

    init {
        if (repository == null) {
            // fake
            viewModelScope.launch {
                _items.emit(
                    arrayListOf(
                        "컴포넌트 예시) 가",
                        "컴포넌트 예시) 나",
                        "컴포넌트 예시) 다",
                    )
                )
            }
        } else {
            // normal
//            selectDummyData()
        }
    }

    private var myPageSEQ: Int = 0
    fun selectDummyData() {

        myPageSEQ++

        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository!!.selectDummyData(page = myPageSEQ)
            }

            _items.emit(response)
        }
    }

    inner class DummyDataSource : PagingSource<Int, String>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {

            return try {
                // 현재 페이지 번호를 가져옵니다.
                val currentPage = params.key ?: 1
                // API를 호출하여 데이터를 가져옵니다.
                val response = repository!!.selectDummyData(page = currentPage)

                // 자체데이터 이기 때문에 무조건 성공이라는 가정으로 반환 로직 실행
                // 이전 페이지와 다음 페이지의 키를 계산합니다.
                val prevKey = if (currentPage > 1) currentPage - 1 else null
                val nextKey = if (response.isNotEmpty()) currentPage + 1 else null

                // LoadResult.Page 객체를 사용하여 데이터와 이전/다음 키를 반환합니다.
                return LoadResult.Page(
                    data = response,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, String>): Int? {
            // 페이징 상태에서 가장 최근에 로드한 페이지를 반환합니다.
//            return state.anchorPosition?.let { anchorPosition ->
//                val anchorPage = state.closestPageToPosition(anchorPosition)
//                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//            }

            return  1
        }
    }
}