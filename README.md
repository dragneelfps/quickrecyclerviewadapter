# Quick RecyclerView Adapter

Simple DSL for the recyclerview adapter.

## Installation

[![](https://jitpack.io/v/dragneelfps/quickrecyclerviewadapter.svg)](https://jitpack.io/#dragneelfps/quickrecyclerviewadapter)

Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency:
```
dependencies {
    implementation 'com.github.dragneelfps:quickrecyclerviewadapter:{latest version}'
}
```

## Usage

For a list of data:
```
private val rvAdapter by lazy {
 adapter(R.layout.item_layout, listOf("One", "Two")) {
     bindings {
         bind<TextView>(R.id.count_text) { datum, countTextView ->
             countTextView.text = datum
         }
     }
 }
}
```
For a flow stream:
```
private val rvAdapter by lazy {
 adapter(R.layout.item_layout, flowOf("One", "Two"), coroutineScope) {
     bindings {
         bind<TextView>(R.id.count_text) { datum, countTextView ->
             countTextView.text = datum
         }
     }
 }
}
