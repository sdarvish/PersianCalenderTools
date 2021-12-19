package health.axon.persiancalendertools.date

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import health.axon.persiancalendertools.date.dialogs.PersianDatePickerDialog

class PersianNumberPicker : NumberPicker {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun addView(child: View) {
        super.addView(child)
        updateView(child)
    }

    override fun addView(
        child: View, index: Int,
        params: ViewGroup.LayoutParams
    ) {
        super.addView(child, index, params)
        updateView(child)
    }

    override fun addView(child: View, params: ViewGroup.LayoutParams) {
        super.addView(child, params)
        updateView(child)
    }

    private fun updateView(view: View) {
        if (view is TextView) {
            PersianDatePicker.typeFace?.let { view.typeface = it }
        }
    }
}