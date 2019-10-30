package com.nooblabs.quickrvadapter

import androidx.annotation.LayoutRes
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * Creates a [RvAdapter].
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
 * @return [RvAdapter]
 */
fun <T> adapter(
    @LayoutRes layout: Int, initialData: List<T> = emptyList(),
    init: AdapterDsl<T>.() -> Unit
): RvAdapter<T> {

    val adapterDsl = AdapterDsl<T>()

    val rvAdapter = RvAdapter(layout, adapterDsl)

    adapterDsl.init()

    return rvAdapter
}

/**
 * Creates a [RvAdapter] from [flow] of data.
 *
 * Example Usage:
 * ```
 * private val rvAdapter by lazy {
 *     adapter(R.layout.item_layout, flowOf("One", "Two")) {
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
 * @param flow flow stream of data
 * @param coroutineScope scope where the [flow] will be collected. Defaults to [GlobalScope]
 * @param init initialization block
 * @return [RvAdapter]
 */
fun <T> adapter(
    @LayoutRes layout: Int,
    flow: Flow<T>,
    coroutineScope: CoroutineScope = GlobalScope,
    init: AdapterDsl<T>.() -> Unit
): RvAdapter<T> {

    val adapterDsl = AdapterDsl<T>()

    val rvAdapter = RvAdapter(layout, adapterDsl)

    adapterDsl.init()

    coroutineScope.launch {
        flow.collect { datum ->
            delay(3000)
            launch(Dispatchers.Main) {
                rvAdapter.addItem(datum)
            }
        }
    }

    return rvAdapter;

}