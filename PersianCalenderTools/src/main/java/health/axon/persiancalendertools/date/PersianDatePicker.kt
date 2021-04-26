package health.axon.persiancalendertools.date

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.NumberPicker
import androidx.constraintlayout.widget.ConstraintLayout
import health.axon.persiancalendertools.R


class PersianDatePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), DatePickerController {

    val datePicker = inflate(context, R.layout.layout_persian_date_picker, this)
    val dayPicker = datePicker.findViewById<NumberPicker>(R.id.dayPicker)
    val monthPicker = datePicker.findViewById<NumberPicker>(R.id.monthPicker)
    val yearPicker = datePicker.findViewById<NumberPicker>(R.id.yearPicker)

    init {
        initializeAttributes(context, attrs)
    }

    override fun initializeAttributes(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val attributes: TypedArray = context.obtainStyledAttributes(
                attrs, R.styleable.PersianDatePicker
            )
            setYearRang(attributes)
            attributes.recycle()
        }
    }

    private fun setYearRang(attrs: TypedArray) {
        val currentYear = 1400
        setMinYear(attrs.getInt(R.styleable.PersianDatePicker_minYear, currentYear - 100))
        setMaxYear(attrs.getInt(R.styleable.PersianDatePicker_maxYear, currentYear + 100))
    }

    override fun setMinYear(minYear: Int) {
        yearPicker.minValue = minYear
    }

    override fun setMaxYear(maxYear: Int) {
        yearPicker.maxValue = maxYear
    }
}