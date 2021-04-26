package health.axon.persiancalendertools.date

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet

interface DatePickerController {
    fun initializeAttributes(context: Context, attrs: AttributeSet?)
    fun setMinYear(minYear : Int)
    fun setMaxYear(maxYear : Int)
    fun setMonthPickerValues(attributes: TypedArray)
}