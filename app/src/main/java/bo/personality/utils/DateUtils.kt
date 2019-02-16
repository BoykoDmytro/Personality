package bo.personality.utils

import android.content.Context
import android.support.annotation.VisibleForTesting
import bo.personality.R
import java.text.SimpleDateFormat
import java.util.*


/*
 * Created by Bo on 10.12.2018.
 */
object DateUtils {

    private const val SECOND_IN_MILLIS = 1000
    private const val MINUTE_IN_MILLIS = 60 * SECOND_IN_MILLIS
    private const val HOUR_IN_MILLIS = 60 * MINUTE_IN_MILLIS
    private const val DAY_IN_MILLIS = 24 * HOUR_IN_MILLIS

    private var hoursFormatter: SimpleDateFormat? = null
    private var dayFormatter: SimpleDateFormat? = null
    private var dayWeekDateFormatter: SimpleDateFormat? = null
    private var dayOfWeekFormatter: SimpleDateFormat? = null
    private var shortDayFormatter: SimpleDateFormat? = null

    private var mCalendar: Calendar? = null

    init {
        //        hoursFormatter = new SimpleDateFormat("HH:mm"); 24 hour format, left just in case
        hoursFormatter = SimpleDateFormat("h:mm a", Locale.getDefault()) //12 hour with am/pm marker
        dayFormatter = SimpleDateFormat("MMMM dd", Locale.getDefault())
        dayWeekDateFormatter = SimpleDateFormat("EEEE MMMM dd, yyyy", Locale.getDefault())
        dayOfWeekFormatter = SimpleDateFormat("EEE", Locale.getDefault())
        shortDayFormatter = SimpleDateFormat("MMM dd", Locale.getDefault())
    }

    fun init(locale: Locale) {
        //        hoursFormatter = new SimpleDateFormat("HH:mm"); 24 hour format, left just in case
        hoursFormatter = SimpleDateFormat("h:mm a", locale) //12 hour with am/pm marker
        dayFormatter = SimpleDateFormat("MMMM dd", locale)
        dayWeekDateFormatter = SimpleDateFormat("EEEE MMMM dd, yyyy", locale)
        dayOfWeekFormatter = SimpleDateFormat("EEE", locale)
        shortDayFormatter = SimpleDateFormat("MMM dd", locale)

        mCalendar = Calendar.getInstance(locale)
    }

    fun getChatTime(dateInSeconds: Long, context: Context): String {
        val now = mCalendar!!.timeInMillis
        val diff = now - dateInSeconds
        return getStringDate(dateInSeconds, diff, context)
    }

    fun getChatDate(date: Long): String {
        return dayFormatter!!.format(Date(date))
    }

    fun getDayWeekDate(date: Long): String {
        return dayWeekDateFormatter!!.format(Date(date))
    }

    fun getHourDate(date: Long): String {
        return hoursFormatter!!.format(Date(date))
    }

    fun getTimeUTCAndLocalTimeDate(date: Long, timeZoneId: String): String {
        val currentTimeZone = TimeZone.getDefault()
        val timeZone = TimeZone.getTimeZone(timeZoneId)
        val timeZoneName = timeZone.getDisplayName(false, TimeZone.SHORT, Locale.US)
        val offset = (timeZone.getOffset(date) - currentTimeZone.getOffset(date)).toLong()
        val split = timeZoneName.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return hoursFormatter!!.format(Date(date + offset)) + " " + split[0] + " (" +
                hoursFormatter!!.format(Date(date)) + " Local Time)"
    }

    fun getStringDate(time: Long, diff: Long, context: Context): String {
        //TODO replace later totally by getActionTimeString(...) to make sure all dates are same in app
        return when {
            diff < MINUTE_IN_MILLIS -> context.getString(R.string.date_now)
            diff < 2 * MINUTE_IN_MILLIS -> context.getString(R.string.date_a_minute_ago)
            diff < HOUR_IN_MILLIS -> (diff / MINUTE_IN_MILLIS).toString() + " " + context.getString(R.string.date_min_ago)
            diff < DAY_IN_MILLIS -> hoursFormatter!!.format(Date(time))
            diff < 2 * DAY_IN_MILLIS -> context.getString(R.string.yesterday)
            else -> dayFormatter!!.format(Date(time))
        }
    }



}