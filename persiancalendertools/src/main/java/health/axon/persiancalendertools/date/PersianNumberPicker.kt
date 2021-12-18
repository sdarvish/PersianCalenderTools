package health.axon.persiancalendertools.date

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import health.axon.persiancalendertools.date.dialogs.PersianDatePickerDialog
import health.axon.persiancalendertools.R

class PersianNumberPicker(context: Context?, attrs: AttributeSet?, defStyleAttr: Int = 0) :
    NumberPicker(
        context,
        attrs,
        defStyleAttr
    ) {

    var typeFace: Typeface? = null

    init {
        getConfigurationAttributes(attrs, defStyleAttr)
    }

    private fun getConfigurationAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val arr = context.obtainStyledAttributes(
            attrs,
            R.styleable.PersianNumberPicker,
            defStyleAttr,
            0
        )

        configViews(arr)
        arr.recycle()
    }

    private fun configViews(arr: TypedArray) {
        typeFace = arr.getFont(R.styleable.PersianNumberPicker_pnpfont);
    }

    override fun addView(child: View) {
        super.addView(child)
        updateView(child)
    }

    override fun addView(
        child: View, index: Int,
        params: ViewGroup.LayoutParams
    ) {
        super.addView(child, index, params)
        updateView(child)
    }

    override fun addView(child: View, params: ViewGroup.LayoutParams) {
        super.addView(child, params)
        updateView(child)
    }


    private fun updateView(view: View) {
        if (view is TextView) {
            Log.w("PersianNumberPicker", "updateView ${typeFace == null}")
            typeFace?.let { view.typeface = it }
        }
    }
}