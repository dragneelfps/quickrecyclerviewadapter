package com.nooblabs.quickrvadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Dsl for [RecyclerView.Adapter].
 */
class AdapterDsl<T> {

    /**
     * Stores a list of [ViewBinding]
     */
    val viewBindings = arrayListOf<ViewBinding<T, out View>>()

    /**
     * Bindings of view to data are declared under this.
     */
    fun bindings(init: Binding<T>.() -> Unit) {
        val binding = Binding<T>(this.viewBindings)
        binding.init()
    }
}