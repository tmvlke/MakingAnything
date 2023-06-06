package wskim.domain.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateUtils {

    private val dateFormat: SimpleDateFormat
        get() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    fun convertDate(date: Date) : String{
        return SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA).format(date)
    }

    fun parseDate(date: String): Date? {
        return try {
            dateFormat.parse(date)
        } catch (e: ParseException) {
            Date()
        }
    }

    /** 몇분전, 방금 전,  */
    private object TimeReferencePoint {
        const val SEC : Long = 1000 * 60L
        const val MIN : Long = 1000 * 60 * 60L
        const val HOUR : Long = 1000 * 60 * 60 * 24L
        const val DAY : Long = 1000 * 60 * 60 * 24 * 30L
        const val MONTH : Long = 1000 * 60 * 60 * 24 * 30L * 12L
    }

    fun formatTimeString(regTime: Long): String {
        val curTime = System.currentTimeMillis()
        val diffTime = (curTime - regTime)

        return when {
            diffTime < TimeReferencePoint.SEC -> {
                "방금 전"
            }
            diffTime < TimeReferencePoint.MIN -> {
                TimeUnit.MILLISECONDS.toMinutes(diffTime).toString() + "분 전"
            }
            diffTime < TimeReferencePoint.HOUR -> {
                TimeUnit.MILLISECONDS.toHours(diffTime).toString() + "시간 전"
            }
            diffTime < TimeReferencePoint.DAY -> {
                TimeUnit.MILLISECONDS.toDays(diffTime).toString() + "일 전"
            }
            diffTime < TimeReferencePoint.MONTH -> {
                (TimeUnit.MILLISECONDS.toDays(diffTime) / 30).toString() + "달 전"
            }
            else -> {
                (TimeUnit.MILLISECONDS.toDays(diffTime) / (30 * 12)).toString() + "년 전"
            }
        }
    }
}