package health.axon.persiancalendertools.utils

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import androidx.annotation.StyleableRes
import androidx.core.content.res.*

fun TypedArray.getFontOrNull(context: Context, @StyleableRes index: Int): Typeface? {
    return if (android.os.Build.VERSION.SDK_INT < 26) {
        val fontId = getResourceId(index, -1)
        if (fontId != -1) {
            return try {
                ResourcesCompat.getFont(context, fontId)
            } catch (e: Exception) {
                null
            }
        }
        null
    } else {
        getFont(index)
    }
}