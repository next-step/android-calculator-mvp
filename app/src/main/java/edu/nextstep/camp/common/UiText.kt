package edu.nextstep.camp.common

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
	data class DynamicText(val text: String): UiText()
	data class StringResource(@StringRes val resId: Int, val formatArgs: List<Any> = emptyList()): UiText()

	fun asString(context: Context): String {
		return when (this) {
			is DynamicText -> text
			is StringResource -> context.getString(resId, formatArgs)
		}
	}
}
