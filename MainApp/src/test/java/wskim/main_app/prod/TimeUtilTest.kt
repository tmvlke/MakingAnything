package wskim.main_app.prod

import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import wskim.baselibrary.utils.DateUtils

@RunWith(MockitoJUnitRunner::class)
class TimeUtilTest {

    @Test
    @DisplayName("초가 잘 나오는지?")
    fun t01() {
        val result = DateUtils.formatTimeString(System.currentTimeMillis())

        print(result)

        assert(result == "방금 전")
    }

    @Test
    @DisplayName("1분전 표시가 잘 나오는지?")
    fun t10() {
        val result = DateUtils.formatTimeString(System.currentTimeMillis() - (1000 * 60))

        print(result)

        assert(result == "1분 전")
    }
}