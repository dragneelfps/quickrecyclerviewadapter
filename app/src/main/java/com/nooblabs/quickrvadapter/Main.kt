package com.nooblabs.quickrvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Creates a [RecyclerView.Adapter].
 *
 * Example Usage:
 * ```
 * private val rvAdapter by lazy {
 *     adapter(R.layout.item_layout, listOf("One", "Two")) {
 *         bindings {
 *             bind<TextView>(R.id.count_text) { datum, countTextView ->
 *                 countTextView.text = datum
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * @param layout id of the item layout
 * @param initialData list of initial data
 * @param init initialization block
 * @return [RecyclerView.Adapter]
 */
fun <T> adapter(
    @LayoutRes layout: Int, initialData: List<T> = emptyList(),
    init: AdapterDsl<T>.() -> Unit
): RecyclerView.Adapter<RvViewHolder> {

    val adapterDsl = AdapterDsl<T>()

    val rvAdapter = object : RecyclerView.Adapter<RvViewHolder>() {

        private val data = initialData

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
            return RvViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
            val datum = data[position]
            adapterDsl.viewBindings.forEach { viewBinding ->
                viewBinding.mapper(datum, holder.itemView.findViewById(viewBinding.viewId))
            }
        }
    }

    adapterDsl.init()

    return rvAdapter
}