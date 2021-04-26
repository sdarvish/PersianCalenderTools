package health.axon.persiancalendertools.date

import android.content.Context
import android.util.AttributeSet

interface DatePickerController {
    fun initializeAttributes(context: Context, attributeSet: AttributeSet?)
    fun setMinYear(minYear : Int)
    fun setMaxYear(maxYear : Int)
}