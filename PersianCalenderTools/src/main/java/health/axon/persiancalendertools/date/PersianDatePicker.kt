package health.axon.persiancalendertools.date

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.NumberPicker
import androidx.constraintlayout.widget.ConstraintLayout
import com.hypotemoose.cal.date.GregorianCalendar
import com.hypotemoose.cal.date.PersianCalendar
import health.axon.persiancalendertools.R
import health.axon.persiancalendertools.utils.MONTH_NAMES
import java.util.*


class PersianDatePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), DatePickerController {

    private val selectedDate = PersianCalendar()
    private val datePicker = inflate(context, R.layout.layout_persian_date_picker, this)
    private val dayPicker = datePicker.findViewById<NumberPicker>(R.id.dayPicker)
    private val monthPicker = datePicker.findViewById<NumberPicker>(R.id.monthPicker)
    private val yearPicker = datePicker.findViewById<NumberPicker>(R.id.yearPicker)

    init {
        initializeAttributes(context, attrs)
        setValueChangeListener()
        setDaysRange()
        setDefaultDate(selectedDate.year, selectedDate.month, selectedDate.day)
    }

    override fun initializeAttributes(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val attributes: TypedArray = context.obtainStyledAttributes(
                it, R.styleable.PersianDatePicker
            )

            setYearRang(attributes)
            setMonthPickerValues(attributes)
            attributes.recycle()
        }
    }

    private fun setYearRang(attrs: TypedArray) {
        val currentYear = selectedDate.year
        setMinYear(attrs.getInt(R.styleable.PersianDatePicker_minYear, currentYear - 100))
        setMaxYear(attrs.getInt(R.styleable.PersianDatePicker_maxYear, currentYear + 100))
    }

    override fun setMonthPickerValues(attributes: TypedArray) {
        val shouldDisplayMonthName = attributes.getBoolean(
            R.styleable.PersianDatePicker_displayMonthName, true
        )
        monthPicker.apply {
            minValue = 1
            maxValue = 12
            if (shouldDisplayMonthName)
                displayedValues = MONTH_NAMES
        }
    }

    override fun setMinYear(minYear: Int) {
        yearPicker.minValue = minYear
    }

    override fun setMaxYear(maxYear: Int) {
        yearPicker.maxValue = maxYear
    }

    override fun setDefaultDate(date: Calendar) {
        val persianCalendar = PersianCalendar(GregorianCalendar(date))
        setDefaultDate(persianCalendar.year, persianCalendar.month, persianCalendar.day)
    }

    override fun setDefaultDate(year: Int, month: Int, day: Int) {
        yearPicker.value = selectedDate.year
        monthPicker.value = selectedDate.month
        dayPicker.value = selectedDate.day
    }


    private fun setValueChangeListener() {
        yearPicker.setOnValueChangedListener(this::onYearValueChanged)
        monthPicker.setOnValueChangedListener(this::onMonthValueChanged)
        dayPicker.setOnValueChangedListener(this::onYearValueChanged)
    }

    private fun onYearValueChanged(picker: NumberPicker, oldVal: Int, newVal: Int) {
        selectedDate.year = newVal
        setDaysRange()
    }

    private fun setDaysRange() {
        dayPicker.minValue = 1
        dayPicker.maxValue = selectedDate.numberOfDaysInMonth
    }

    private fun onMonthValueChanged(picker: NumberPicker, oldVal: Int, newVal: Int) {
        selectedDate.month = newVal
        setDaysRange()
    }

    private fun isLeapYear() = selectedDate.isLeapYear

    private fun onDayValueChanged(picker: NumberPicker, oldVal: Int, newVal: Int) {
        selectedDate.day = newVal
    }
}