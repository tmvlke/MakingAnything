package wskim.main_app.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.greenrobot.eventbus.EventBus
import wskim.baselibrary.proguard_safe_zone.network.vo.KakaoIntegrationContentListVO
import wskim.baselibrary.proguard_safe_zone.network.vo.KakaoIntegrationContentVO
import wskim.main_app.mvvm.repository.SearchResultRepository
import javax.inject.Inject
import kotlin.streams.toList

@HiltViewModel
class BucketViewModel @Inject constructor(
    private val repository : SearchResultRepository
) : ViewModel(){

    private val _contentList : MutableLiveData<KakaoIntegrationContentListVO> = MutableLiveData<KakaoIntegrationContentListVO>()
    val contentList: LiveData<KakaoIntegrationContentListVO> = _contentList

    fun setUp() {
        val selectBucketList =  repository.selectBucketList()

        // 최신 순서로 정렬
        val resultList = selectBucketList.list.stream().sorted(
            Comparator.comparing(KakaoIntegrationContentVO::saveDateTime).reversed()
        ).toList() as ArrayList<KakaoIntegrationContentVO>

        _contentList.value = KakaoIntegrationContentListVO(
            list = resultList,
            listAddTypeEnum = KakaoIntegrationContentListVO.ListAddTypeEnum.REFRESH
        )
    }

    fun deleteBucketItem(
        position : Int
    ) {
        val selectBucketList =  repository.selectBucketList()

        // api 에서 pk 로 삼을 수 있는 인덱스가 없어서 그나마 분별력 있는 정보인
        // 일자와 썸네일 주소를 통해 조회, 저장을 하게 되었습니다.
        _contentList.value!!.list[position].also {

            // 리스트에서 제외하기
            it.isBucketYn = false

            val resultList = KakaoIntegrationContentListVO(
                list = selectBucketList.list.stream()
                    .filter {value -> !(value.datetime == it.datetime && value.thumbnail == it.thumbnail)}
                    .toList() as ArrayList<KakaoIntegrationContentVO>,
                listAddTypeEnum = KakaoIntegrationContentListVO.ListAddTypeEnum.ADD
            )

            // 저장하기
            repository.updateBucketList(resultList)

            // 검색 결과 페이지에 변화 주기
            EventBus.getDefault().post(SearchResultViewModel.EventBusUpdateBucketItem(it))
        }

        // 버킷 페이지 리스트 내에서 삭제하기
        _contentList.value!!.list.removeAt(position)

        // 버킷 페이지 ui 변화 주기
        _contentList.value = KakaoIntegrationContentListVO(
            list = _contentList.value!!.list,
            listAddTypeEnum = KakaoIntegrationContentListVO.ListAddTypeEnum.DELETE,
            deletePosition = position
        )
    }
}