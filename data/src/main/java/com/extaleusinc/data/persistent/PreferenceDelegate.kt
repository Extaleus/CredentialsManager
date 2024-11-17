package com.extaleusinc.data.persistent

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty

internal inline operator fun <reified T> SharedPreferences.getValue(
    thisRef: Any,
    property: KProperty<*>
): T? {
    return when (T::class) {
        Boolean::class -> {
            if (contains(property.name)) {
                getBoolean(property.name, false)
            } else null
        }

        Int::class -> {
            if (contains(property.name)) {
                getInt(property.name, 0)
            } else null
        }

        Float::class -> {
            if (contains(property.name)) {
                getFloat(property.name, 0.0f)
            } else null
        }

        Long::class -> {
            if (contains(property.name)) {
                getLong(property.name, 0L)
            } else null
        }

        String::class -> getString(property.name, null)
        else -> throw UnsupportedOperationException()
    } as? T
}

internal operator fun <T> SharedPreferences.setValue(
    thisRef: Any,
    property: KProperty<*>,
    value: T
) {
    edit {
        when (value) {
            is Boolean -> putBoolean(property.name, value)
            is Int -> putInt(property.name, value)
            is Float -> putFloat(property.name, value)
            is Long -> putLong(property.name, value)
            is String -> putString(property.name, value)
            else -> throw UnsupportedOperationException()
        }
    }
}
