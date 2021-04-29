package health.axon.persiancalendertools.date

import com.hypotemoose.cal.date.PersianCalendar

interface OnDateSelectedListener {
    fun onDateSet(persianCalendar: PersianCalendar)
}