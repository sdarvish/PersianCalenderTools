package health.axon.persiancalendertools.date

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.NumberPicker
import androidx.constraintlayout.widget.ConstraintLayout
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

    override fun setDefaultDate(date: Date) {
        TODO("Not yet implemented")
    }

    override fun setDefaultDate(year: Int, month: Int, day: Int) {
        TODO("Not yet implemented")
    }

    override fun setTodayAsDefaultDate() {
        TODO("Not yet implemented")
    }


    private fun setValueChangeListener() {
        yearPicker.setOnValueChangedListener(this::onYearValueChanged)
        monthPicker.setOnValueChangedListener(this::onYearValueChanged)
        dayPicker.setOnValueChangedListener(this::onYearValueChanged)
    }

    private fun onYearValueChanged(picker: NumberPicker, oldVal: Int, newVal: Int) {

        selectedDate.year = newVal
        updateDayValues()
    }

    private fun updateDayValues(newSelectedMonth: Int? = null) {
        dayPicker.minValue = 1
        val selectedMonth = newSelectedMonth ?: monthPicker.value
        when {
            selectedMonth in 1..6 -> dayPicker.maxValue = 31
            selectedMonth in 1..11 -> dayPicker.maxValue = 30
            selectedMonth == 12 && isLeapYear() -> dayPicker.maxValue = 30
            selectedMonth == 12 && !isLeapYear() -> dayPicker.maxValue = 29

        }
    }

    private fun onMonthValueChanged(picker: NumberPicker, oldVal: Int, newVal: Int) {
        selectedDate.month = newVal
        updateDayValues(newVal)
    }

    private fun isLeapYear() = selectedDate.isLeapYear

    private fun onDayValueChanged(picker: NumberPicker, oldVal: Int, newVal: Int) {
        selectedDate.day = newVal
    }
}