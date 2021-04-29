package health.axon

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hypotemoose.cal.date.GregorianCalendar
import health.axon.persiancalendertools.date.OnDateSelectedListener
import health.axon.persiancalendertools.date.dialogs.PersianDatePickerDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.openDialogFragment).setOnClickListener {

            PersianDatePickerDialog().setOnDateSelectedListener(object : OnDateSelectedListener {
                override fun onDateSet(persianCalendar: health.axon.persiancalendertools.date.PersianCalendar) {

                    val gregorianCalendar = GregorianCalendar(persianCalendar)
                    Log.d("OnDateSelected", ": ${persianCalendar.date}")
                    Log.d("OnDateSelected", ": ${persianCalendar.toDate()}")
                    Log.d("OnDateSelected", ": ${gregorianCalendar.date}")
                }
            }).show(supportFragmentManager, "")
        }
    }
}