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
import health.axon.persiancalendertools.utils.getFontOrNull
import android.R.color
import android.nfc.Tag

import android.widget.EditText
import java.lang.IllegalArgumentException


class PersianNumberPicker @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.numberPickerStyle,
    defStyleRes: Int = 0
) : NumberPicker(context, attrs, defStyleAttr, defStyleRes) {

    var typeFace: Typeface? = null

    val TAG = PersianNumberPicker::class.java.simpleName
    init {
        Log.e(TAG, "#WTF INIT")
        getConfigurationAttributes(attrs, 0)
    }

    override fun addView(child: View?, index: Int) {
        super.addView(child, index)
        Log.e(TAG, "#WTF addView Index")
    }

    override fun addView(child: View?, width: Int, height: Int) {
        super.addView(child, width, height)
        Log.e(TAG, "#WTF addView width,height")
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
        Log.e(TAG, "WTF addView2: is TypeFace==null == ${typeFace == null} ")
        Log.e(TAG, "WTF addView2: ${child::class.java.simpleName}")
        updateView(child)
    }

    override fun addView(
        child: View, index: Int,
        params: ViewGroup.LayoutParams
    ) {
        super.addView(child, index, params)
        Log.e(TAG, "WTF addView2: is TypeFace==null == ${typeFace == null} ")
        Log.e(TAG, "WTF addView2: ${child::class.java.simpleName}")
        updateView(child)
    }

    override fun addView(child: View, params: ViewGroup.LayoutParams) {
        super.addView(child, params)
        Log.e(TAG, "WTF addView3: is TypeFace==null == ${typeFace == null} ")
        Log.e(TAG, "WTF addView3: ${child::class.java.simpleName}")
        updateView(child)
    }

    private fun updateView(view: View) {
        if (view is TextView) {
            Log.e(TAG, "#WTF updateView: is TypeFace==null == ${typeFace == null}")
            typeFace?.let { view.typeface = it }
        }
    }

    
}