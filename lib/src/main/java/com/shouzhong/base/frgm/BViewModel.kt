package com.shouzhong.base.frgm

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shouzhong.base.act.BActivity
import com.shouzhong.bridge.FragmentStack
import kotlinx.coroutines.CoroutineScope

abstract class BViewModel : ViewModel(), LifecycleObserver {
    var uniqueId: String? = null

    open fun init() = Unit

    open fun onFragmentFirstVisible() = Unit

    open fun onFragmentVisibleChange(isVisible: Boolean) = Unit

    open fun onCreate(savedInstanceState: Bundle?) = Unit

    open fun onViewCreated(view: View, savedInstanceState: Bundle?) = Unit

    open fun onActivityCreated(savedInstanceState: Bundle?) = Unit

    open fun onStart() = Unit

    open fun onResume() = Unit

    open fun onPause() = Unit

    open fun onStop() = Unit

    open fun onDestroyView() = Unit

    open fun onDestroy() = Unit

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = Unit

    open fun onConfigurationChanged(newConfig: Configuration) = Unit

    open fun onSaveInstanceState(outState: Bundle) = Unit

    fun getLifecycleOwner(): LifecycleOwner = getFragment()

    fun <T : BFragment<*>> getFragment(): T = FragmentStack.getFragment(uniqueId) as T

    fun <T : BActivity<*>> getActivity(): T = getFragment<BFragment<*>>().activity as T

    fun <T : ViewDataBinding> getBinding(): T? = try {
        getFragment<BFragment<*>>().getBinding()
    } catch (e: Throwable) {
        null
    }

    fun getScope(): CoroutineScope = viewModelScope
}