package com.nooblabs.quickrvadapter

import android.view.View
import androidx.annotation.IdRes

/**
 * Contains bindings of view to data.
 *
 * @param bindings list of bindings provided from the [AdapterDsl].
 */
class Binding<T>(val bindings: ArrayList<ViewBinding<T, out View>>) {

    /**
     * Binds [viewId] according to [mapper]
     */
    fun <V : View> bind(@IdRes viewId: Int, mapper: (T, V) -> Unit) {
        val viewBinding = ViewBinding(viewId, mapper)
        bindings.add(viewBinding)
    }
}