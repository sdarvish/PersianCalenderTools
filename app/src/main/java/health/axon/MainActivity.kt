package health.axon

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import health.axon.persiancalendertools.date.dialogs.PersianDatePickerDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.openDialogFragment).setOnClickListener {
            PersianDatePickerDialogBuilder()
                .build().show(supportFragmentManager, "OpenDatePicker")

        }
    }
}