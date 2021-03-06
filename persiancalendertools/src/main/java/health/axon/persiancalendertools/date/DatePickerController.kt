package health.axon.persiancalendertools.date

import android.content.res.TypedArray
import android.graphics.Typeface

import java.util.*

interface DatePickerController {
    fun setMinYear(minYear: Int)
    fun setMaxYear(maxYear: Int)
    fun setMonthPickerValues(attributes: TypedArray)
    fun setDefaultDate(date: Calendar)
    fun setDefaultDate(year: Int, month: Int, day: Int)
    fun getSelectedDate(): PersianCalendar
    fun getSelectedDateAsGregorian(): Date
    fun getSelectedYear(): Int
    fun getSelectedMonth(): Int
    fun getSelectedMonthName(): String
    fun getSelectedDay(): Int
    fun setTypeFace(typeface: Typeface)
    fun getSelectedGregorianYear(): Int
    fun getSelectedGregorianMonth(): Int
    fun getSelectedGregorianMonthName(): String
    fun getSelectedGregorianDay(): Int
    fun setOnSelectedDateChangedListener(listener: OnSelectedDateChangedListener)
}