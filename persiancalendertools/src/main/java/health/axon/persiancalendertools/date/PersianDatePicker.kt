package health.axon.persiancalendertools.date

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.hypotemoose.cal.date.GregorianCalendar
import health.axon.persiancalendertools.R
import health.axon.persiancalendertools.utils.MONTH_NAMES
import java.util.*


class PersianDatePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), DatePickerController {

    private var selectedDate = PersianCalendar()
    private val selectedGregorianDate = Calendar.getInstance()
    private val datePicker = inflate(context, R.layout.layout_persian_date_picker, this)
    private val dayPicker = datePicker.findViewById<PersianNumberPicker>(R.id.dayPicker)
    private val monthPicker = datePicker.findViewById<PersianNumberPicker>(R.id.monthPicker)
    private val yearPicker = datePicker.findViewById<PersianNumberPicker>(R.id.yearPicker)
    private var onSelectedDateChangedListener: OnSelectedDateChangedListener? = null

    init {
        initializeAttributes(context, attrs)
        setValueChangeListener()
        setDaysRange()
        setDefaultDate(selectedDate.year, selectedDate.month, selectedDate.day)
    }

    private fun initializeAttributes(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val attributes: TypedArray = context.obtainStyledAttributes(
                it, R.styleable.PersianDatePicker
            )

            setYearRang(attributes)
            setMonthPickerValues(attributes)
            attributes.recycle()
        }
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
        selectedDate = persianCalendar

        setDefaultDate(persianCalendar.year, persianCalendar.month, persianCalendar.day)
    }

    override fun getSelectedDate() = selectedDate

    override fun getSelectedDateAsGregorian(): Date = selectedGregorianDate.time


    override fun getSelectedYear(): Int = selectedDate.year


    override fun getSelectedMonth() = selectedDate.month

    override fun getSelectedMonthName() = MONTH_NAMES[selectedDate.month - 1]
    override fun setOnSelectedDateChangedListener(listener: OnSelectedDateChangedListener) {
        this.onSelectedDateChangedListener = listener
    }

    override fun getSelectedDay() = selectedDate.day
    override fun getSelectedGregorianYear() = selectedGregorianDate.get(Calendar.YEAR)
    override fun getSelectedGregorianMonth() = selectedGregorianDate.get(Calendar.MONTH)
    override fun getSelectedGregorianMonthName(): String {
        TODO("Not yet implemented")
    }

    override fun getSelectedGregorianDay() = selectedGregorianDate.get(Calendar.DAY_OF_MONTH)

    override fun setDefaultDate(year: Int, month: Int, day: Int) {
        selectedDate = PersianCalendar(year, month, day)
        yearPicker.value = selectedDate.year
        monthPicker.value = selectedDate.month
        dayPicker.value = selectedDate.day
    }


    private fun setValueChangeListener() {
        yearPicker.setOnValueChangedListener { _, _, newVal ->
            selectedDate.year = newVal
            setDaysRange()
            selectedDateChanged()
        }
        monthPicker.setOnValueChangedListener { _, _, newVal ->
            selectedDate.month = newVal
            setDaysRange()
            selectedDateChanged()
        }
        dayPicker.setOnValueChangedListener { _, _, newVal ->
            selectedDate.day = newVal
            selectedDateChanged()
        }
    }

    private fun selectedDateChanged() {
        setGregorianDate()
        onSelectedDateChangedListener?.onDateSelected(this)
    }


    private fun setGregorianDate() {
        val gregorianCalendar = GregorianCalendar(selectedDate)

        selectedGregorianDate.set(
            gregorianCalendar.year,
            gregorianCalendar.month,
            gregorianCalendar.day,
            0,
            0,
            0
        )
    }

    private fun setDaysRange() {
        dayPicker.minValue = 1
        dayPicker.maxValue = selectedDate.numberOfDaysInMonth
    }

    private fun setYearRang(attrs: TypedArray) {
        val currentYear = selectedDate.year
        setMinYear(attrs.getInt(R.styleable.PersianDatePicker_minYear, currentYear - 100))
        setMaxYear(attrs.getInt(R.styleable.PersianDatePicker_maxYear, currentYear + 100))
    }

}