package health.axon.persiancalendertools.date

import com.hypotemoose.cal.date.Almanac
import com.hypotemoose.cal.util.AlmanacConverter
import health.axon.persiancalendertools.utils.MONTH_NAMES
import health.axon.persiancalendertools.utils.WEEK_DAY_NAMES
import java.util.*
class PersianCalendar : com.hypotemoose.cal.date.PersianCalendar {

    constructor() : super()
    constructor(a: Almanac?) : super(AlmanacConverter.toPersianCalendar(a))
    constructor(date: PersianCalendar) : super(date.year, date.month, date.day)
    constructor(year: Int, month: Int, day: Int) : super(year, month, day)

    override fun getMonthName(): String {
        return MONTH_NAMES[month - 1]
    }

    override fun getMonths(): Array<String> {
        return MONTH_NAMES
    }

    override fun getWeekDays(): Array<String> {
        return WEEK_DAY_NAMES
    }

    override fun getWeekDay(): String {
        return WEEK_DAY_NAMES[this.weekDayNumber]
    }

    fun toGregorianCalendar(): Calendar {
        val cal = com.hypotemoose.cal.date.GregorianCalendar(this)
        return GregorianCalendar(cal.year, cal.month - 1, cal.day)
    }

    fun toDate(): Date {
        return toGregorianCalendar().time
    }
}