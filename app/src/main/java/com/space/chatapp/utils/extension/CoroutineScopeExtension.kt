package com.space.chatapp.utils.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun LifecycleOwner.launchWithLifecycle(
    block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch { block() }
}

fun ViewModel.viewModelScope(
    coroutineContext: CoroutineContext = Dispatchers.Main,
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(coroutineContext) { block() }
}

fun Fragment.lifecycleScope(block: suspend CoroutineScope.() -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        block()
    }
}