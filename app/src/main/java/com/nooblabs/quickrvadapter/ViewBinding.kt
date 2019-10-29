package com.nooblabs.quickrvadapter

import android.view.View

/**
 * Single binding between view with id [viewId] according to [mapper].
 */
class ViewBinding<T, V : View>(val viewId: Int, val mapper: (T, V) -> Unit)