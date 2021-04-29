package health.axon.persiancalendertools.date.dialogs

import androidx.appcompat.app.AppCompatDialogFragment

class PersianDatePickerDialogBuilder() {
    private val persianDatePickerDialog = PersianDatePickerDialog()

    fun setTitleText(title: String?): AppCompatDialogFragment = persianDatePickerDialog.apply {
        this.title = title
    }

    fun setButtonText(text: String?): AppCompatDialogFragment = persianDatePickerDialog.apply {
        this.buttonText = title
    }

    fun build(): AppCompatDialogFragment {
        return persianDatePickerDialog
    }
}