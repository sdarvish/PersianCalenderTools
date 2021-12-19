package health.axon

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.hypotemoose.cal.date.GregorianCalendar
import health.axon.persiancalendertools.date.OnDateSelectedListener
import health.axon.persiancalendertools.date.PersianCalendar
import health.axon.persiancalendertools.date.PersianNumberPicker
import health.axon.persiancalendertools.date.dialogs.PersianDatePickerDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.openDialogFragment).setOnClickListener {

            val persianCalender = PersianCalendar(1390, 5, 3)

            PersianDatePickerDialog()
                .setDefaultDate(persianCalender.toGregorianCalendar())
                .setOnDateSelectedListener(object : OnDateSelectedListener {
                    override fun onDateSet(persianCalendar: health.axon.persiancalendertools.date.PersianCalendar) {

                        val gregorianCalendar = GregorianCalendar(persianCalendar)
                        Log.d("OnDateSelected", ": ${persianCalendar.date}")
                        Log.d("OnDateSelected", ": ${persianCalendar.toDate()}")
                        Log.d("OnDateSelected", ": ${gregorianCalendar.date}")
                    }
                }).setTypeFace(ResourcesCompat.getFont(this, R.font.iran_yekan_bold)!!)
                .show(supportFragmentManager, "")
        }

        findViewById<PersianNumberPicker>(R.id.numberPicker).also {
            it.minValue = 0
            it.maxValue = 9
            it.displayedValues = arrayOf("سلام","خوبی","خوشی","چه خبر","خوبی","خوشی","چه خبر","خوبی","خوشی","چه خبر")
        }
    }
}