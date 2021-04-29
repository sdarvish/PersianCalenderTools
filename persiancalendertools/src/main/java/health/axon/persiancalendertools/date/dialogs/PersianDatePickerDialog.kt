package health.axon.persiancalendertools.date.dialogs

import android.app.Dialog
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import health.axon.persiancalendertools.R

internal class PersianDatePickerDialog : BottomSheetDialogFragment() {

    private lateinit var root: View
    var title: String? = null
    var buttonText: String? = null
    override fun setupDialog(dialog: Dialog, style: Int) {
        root = View.inflate(context, R.layout.dialog_persian_date_picker_bottom_sheet, null)
        initializeViews()
        setRootBackground(dialog)
    }

    private fun initializeViews() {
        configTitle()
        configButton()
    }

    private fun configButton() {
        val button = root.findViewById<MaterialButton>(R.id.selectDateButton)
        buttonText?.let {
            button.text = it
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

}