package health.axon.persiancalendertools.date.dialogs

import android.app.Dialog
import android.graphics.Typeface
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import health.axon.persiancalendertools.R
import health.axon.persiancalendertools.date.OnDateSelectedListener
import health.axon.persiancalendertools.date.PersianDatePicker
import java.util.*

class PersianDatePickerDialog() : BottomSheetDialogFragment() {

    private var title: String? = null
    private var buttonText: String? = null
    private var maxYear: Int? = null
    private var minYear: Int? = null
    private var defaultYear: Int? = null
    private var defaultMonth: Int? = null
    private var defaultDay: Int? = null
    private var onDateSelectListener: OnDateSelectedListener? = null
    private lateinit var datePickers: PersianDatePicker
    private lateinit var root: View

    override fun setupDialog(dialog: Dialog, style: Int) {
        root = View.inflate(context, R.layout.dialog_persian_date_picker_bottom_sheet, null)
        initializeViews()
        setRootBackground(dialog)
    }


    fun setMinYear(minYear: Int) = apply {
        this.minYear = minYear

    }

    fun setMaxYear(maxYear: Int) = apply {
        this.maxYear = maxYear
    }


    fun setDefaultDate(date: Calendar) = apply {
        setDefaultDate(
            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_MONTH)
        )
    }

    fun setDefaultDate(year: Int, month: Int, day: Int) = apply {
        this.defaultYear = year
        this.defaultMonth = month
        this.defaultDay = day
    }

    fun setTitleText(title: String?) = apply {
        this.title = title
    }

    fun setButtonText(text: String) = apply {
        this.buttonText = text
    }

    fun setOnDateSelectedListener(listener: OnDateSelectedListener) = apply {
        this.onDateSelectListener = listener
    }

    fun setTypeFace(typeface: Typeface) = apply {
        PersianDatePickerDialog.typeFace = typeface
    }

    private fun initializeViews() {
        configTitle()
        configButton()
        configCloseButton()
        initializeDatePicker()
    }

    private fun configCloseButton() {
        val button = root.findViewById<ImageView>(R.id.closeDatePicker)
        button.setOnClickListener { dismiss() }
    }

    private fun initializeDatePicker() {
        datePickers = root.findViewById(R.id.datePicker)

        minYear?.let { datePickers.setMinYear(it) }
        maxYear?.let { datePickers.setMinYear(it) }
        if (defaultYear != null && defaultMonth != null && defaultDay != null)
            datePickers.setDefaultDate(defaultYear!!, defaultMonth!!, defaultDay!!)

    }

    private fun configButton() {
        val button = root.findViewById<MaterialButton>(R.id.selectDateButton)
        buttonText?.let {
            button.text = it
        }

        button.setOnClickListener {
            onDateSelectListener?.onDateSet(datePickers.getSelectedDate())
            dismiss()
        }
    }

    private fun configTitle() {
        val titleTextView: TextView = root.findViewById(R.id.datePickerDialogTitle)
        if (title != null) {
            titleTextView.text = title
            titleTextView.visibility = VISIBLE
        } else {
            titleTextView.visibility = GONE
        }
    }

    private fun setRootBackground(dialog: Dialog) {
        dialog.setContentView(root)
        (root.parent as View).setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.transparent
            )
        )
    }

    companion object {
        var typeFace: Typeface? = null
    }

}