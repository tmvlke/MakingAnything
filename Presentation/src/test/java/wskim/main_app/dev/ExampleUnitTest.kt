package wskim.main_app.dev

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import wskim.data.repository.LayoutExampleInfinityScrollRepository
import wskim.main_app.viewmodel.LayoutExampleInfinityScrollViewModel

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    // ViewModel 테스트를 위한 Rule
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // 테스트할 ViewModel 인스턴스
    private lateinit var viewModel: LayoutExampleInfinityScrollViewModel

    //
    private lateinit var repository : LayoutExampleInfinityScrollRepository

    // Mock으로 생성된 Observer
    @Mock
    private lateinit var mockObserver: Observer<String>

//    @Before
//    fun setup() {
//        // SavedStateHandle 모의 객체 생성
//        val mockSavedStateHandle = mock(LayoutExampleInfinityScrollRepository::class.java)
//
//        // ViewModel 인스턴스 생성
//        viewModel = LayoutExampleInfinityScrollViewModel(mockSavedStateHandle)
//
//    }

    private fun makeDummyList(): ArrayList<String> {
        val dummyList = arrayListOf<String>()

        repeat(1000) {
            dummyList.add("${it}번 데이터")
        }
        return dummyList
    }

    suspend fun selectDummyData(
        page: Int = 1,
        count: Int = 20
    ) : ArrayList<String> {
        val allData = makeDummyList()

        return allData.asFlow().drop((page-1) * count).take(count).toList() as ArrayList<String>
    }
    @Test
    fun addition_isCorrect() = runBlocking {
        val response = selectDummyData(10)

        assert(true)
    }
}