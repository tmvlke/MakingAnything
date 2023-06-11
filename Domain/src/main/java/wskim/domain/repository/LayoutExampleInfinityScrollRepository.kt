package wskim.domain.repository

interface LayoutExampleInfinityScrollRepository {

    suspend fun selectDummyData(
        page: Int = 1,
        count: Int = 20
    ): ArrayList<String>
}