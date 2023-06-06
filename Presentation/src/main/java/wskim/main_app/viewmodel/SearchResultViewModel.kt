package wskim.main_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import wskim.data.network.NetworkResult
import wskim.data.repository.SearchResultRepository
import wskim.domain.proguard_safe_zone.network.enum.KakaoIntegrationContentTypeEnum
import wskim.domain.proguard_safe_zone.network.vo.KakaoIntegrationContentListVO
import wskim.domain.proguard_safe_zone.network.vo.KakaoIntegrationContentVO
import wskim.domain.proguard_safe_zone.vo.UpdateBucketStateVO
import java.util.Date
import javax.inject.Inject
import kotlin.streams.toList

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val repository : SearchResultRepository
) : ViewModel(){

    private val _initUi : MutableLiveData<Any> = MutableLiveData<Any>()
    val initUi: LiveData<Any> = _initUi

    private val _contentList : MutableLiveData<KakaoIntegrationContentListVO> = MutableLiveData<KakaoIntegrationContentListVO>()
    val contentList: LiveData<KakaoIntegrationContentListVO> = _contentList

    private val _bucketStateChanged : MutableLiveData<UpdateBucketStateVO> = MutableLiveData<UpdateBucketStateVO>()
    val bucketStateChanged: LiveData<UpdateBucketStateVO> = _bucketStateChanged

    private var isRegister = false
    fun setUp() {
        if(!isRegister){
            isRegister = true
            EventBus.getDefault().register(this)
        }
        _initUi.value = Any()
    }


    private val searchSize = 10
    private var tempKeyword = ""
    private var tempNowImagePage = 1
    private var tempNowVClipPage = 1

    private var isTempImageEnd = false
    private var isTempVClipEnd = false

    private var isSearchTrying = false
    fun selectKeywordContent(keyword: String?, isContinue: Boolean) {
        val searchKeyword = if (keyword != null) {
            tempKeyword = keyword
            keyword
        } else {
            tempKeyword.ifEmpty {
                return
            }
        }

        // 페이징 처리가 필요한 경우 페이지의 넘버링을 추가한다.
        if (isContinue) {

            // 중복 페이징 처리 방지용
            if (isSearchTrying) {
                return
            }

            // 리스트가 둘다 존재하지 않는다면 조회를 시도하지 않는다.
            if (isTempImageEnd && isTempVClipEnd) {
                return
            }

            if (!isTempImageEnd)
                tempNowImagePage += 1

            if (!isTempVClipEnd)
                tempNowVClipPage += 1

        } else {
            // 초기화
            tempNowImagePage = 1
            tempNowVClipPage = 1

            isTempImageEnd = false
            isTempVClipEnd = false
        }

        // 검색 시작
        isSearchTrying = true

        viewModelScope.launch(Dispatchers.Default) {

            val tempList = arrayListOf<KakaoIntegrationContentVO>()

            // 이미지 검색
            val response1 = withContext(Dispatchers.IO) {
                if (!isTempImageEnd) {
                    repository.selectKakaoImageList(
                        keyword = searchKeyword,
                        page = tempNowImagePage,
                        size = searchSize
                    )
                } else {
                    NetworkResult(arrayListOf(), null)
                }
            }

            if (response1.isSuccessful) {

                if (!isTempImageEnd) {
                    isTempImageEnd = response1.getMeta()!!.is_end

                    for (i in response1.getDocuments()!!) {
                        tempList.add(
                            KakaoIntegrationContentVO(
                                title = i.display_sitename,
                                thumbnail = i.thumbnail_url,
                                datetime = i.datetime,
                                contentTypeEnum = KakaoIntegrationContentTypeEnum.IMAGE,
                                isBucketYn = false,
                                saveDateTime = null
                            )
                        )
                    }
                }
            }

            // 비디오 검색
            val response2 = withContext(Dispatchers.IO) {
                if (!isTempVClipEnd) {
                    repository.selectKakaoVclipList(
                        keyword = searchKeyword,
                        page = tempNowVClipPage,
                        size = searchSize
                    )
                } else {
                    NetworkResult(arrayListOf(), null)
                }
            }

            if (response2.isSuccessful) {

                if (!isTempVClipEnd) {
                    isTempVClipEnd = response2.getMeta()!!.is_end

                    for (i in response2.getDocuments()!!) {
                        tempList.add(
                            KakaoIntegrationContentVO(
                                title = i.title,
                                thumbnail = i.thumbnail,
                                datetime = i.datetime,
                                contentTypeEnum = KakaoIntegrationContentTypeEnum.VCLIP,
                                isBucketYn = false,
                                saveDateTime = null
                            )
                        )
                    }
                }
            }

            val selectBucketList =  repository.selectBucketList()

            // 검색 결과에서는 보관함 저장 일자는 초기화 하여 사용해야함
            selectBucketList.list.forEach {
                it.saveDateTime = null
            }

            // 기존에 버킷에 저장한 이력이 있는지 확인
            val sameList = selectBucketList.list.stream()
                .filter { origin ->
                    tempList.stream()
                        .anyMatch { new ->
                            origin.title == new.title && origin.thumbnail == new.thumbnail
                        }
                }
                .toList() as ArrayList<KakaoIntegrationContentVO>

            val diffList = tempList.stream()
                .filter { origin ->
                    selectBucketList.list.stream()
                        .allMatch { new ->
                            !(origin.title == new.title && origin.thumbnail == new.thumbnail)
                        }
                }
                .toList()

            sameList.addAll(diffList)

            // 최신 순서로 정렬
            val resultList = sameList.stream().sorted(
                Comparator.comparing(KakaoIntegrationContentVO::datetime).reversed()
            ).toList() as ArrayList<KakaoIntegrationContentVO>

            // 페이징 처리 중이라면 기존 리스트에 추가하여 반환하기 위함
            if(isContinue) {
                // 추가 결과가 있는 경우에만 시도하기
                if (resultList.isNotEmpty()) {

                    _contentList.postValue(
                        KakaoIntegrationContentListVO(
                            list = _contentList.value!!.list.apply {
                                addAll(resultList)
                            },
                            listAddTypeEnum = KakaoIntegrationContentListVO.ListAddTypeEnum.ADD
                        )
                    )
                }

                isSearchTrying = false
            } else {
                _contentList.postValue(
                    KakaoIntegrationContentListVO(
                        list = resultList,
                        listAddTypeEnum = KakaoIntegrationContentListVO.ListAddTypeEnum.REFRESH
                    )
                )
                isSearchTrying = false
            }
        }
    }

    private fun baseUpdateBucketItem(contentVO: KakaoIntegrationContentVO, isBucketPage: Boolean) {

        // 버킷 정보 업데이트 전에 기존 버킷 정보와 비교하기 위해
        // 버킷 리스트 조회합니다.
        val selectBucketList =  repository.selectBucketList()

        // api 에서 pk 로 삼을 수 있는 인덱스가 없어서 그나마 분별력 있는 정보인
        // 일자와 썸네일 주소를 통해 조회, 저장을 하게 되었습니다.
        var isExist = false
        for (i in selectBucketList.list) {
            if (
                i.datetime == contentVO.datetime && i.thumbnail == contentVO.thumbnail
            ) {
                isExist = true
                break
            }
        }

        // 버킷 페이지에서 ui 에 변화만 필요하기 때문에 로직을 제외한다.
        if (!isBucketPage) {
            val resultList = if (isExist) {
                // 있으면 리스트에서 제외하기
                contentVO.isBucketYn = false

                KakaoIntegrationContentListVO(
                    list = selectBucketList.list.stream()
                        .filter {value -> !(value.datetime == contentVO.datetime && value.thumbnail == contentVO.thumbnail)}
                        .toList() as ArrayList<KakaoIntegrationContentVO>,
                    listAddTypeEnum = KakaoIntegrationContentListVO.ListAddTypeEnum.ADD
                )
            } else {
                // 없으면 추가하기
                contentVO.isBucketYn = true
                contentVO.saveDateTime = Date()

                selectBucketList.list.add(0, contentVO)
                selectBucketList
            }

            // 저장하기
            repository.updateBucketList(resultList)
        }

        // 검색 결과 ui에 변화를 주기 위해
        // 검색 결과 내의 위치를 탐색한다.
        _contentList.value?.list?.also {
            for (i in 0 until _contentList.value!!.list.size) {
                if (it[i].datetime == contentVO.datetime && it[i].thumbnail == contentVO.thumbnail) {
                    // 검색 결과 ui 변화 주기
                    _bucketStateChanged.value = UpdateBucketStateVO(
                        position = i,
                        title = contentVO.title,
                        isBucketYn = contentVO.isBucketYn
                    )
                }
            }
        }
    }
    fun updateBucketItem(
        position : Int
    ) {
        _contentList.value!!.list[position].also {
            baseUpdateBucketItem(it, false)
        }
    }

    data class EventBusUpdateBucketItem(val contentVo: KakaoIntegrationContentVO)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventBusUpdateBucketItem(eventBusVO: EventBusUpdateBucketItem) {
        baseUpdateBucketItem(eventBusVO.contentVo, true)
    }

    override fun onCleared() {
        if(isRegister){
            isRegister = false
            EventBus.getDefault().unregister(this)
        }
        super.onCleared()
    }
}