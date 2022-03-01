package com.example.jetpackmvvm.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.jetpackmvvm.base.viewmodel.BaseViewModel
import com.example.jetpackmvvm.ext.inflateBinding

abstract class BaseVmVbFragment<VM : BaseViewModel, VB : ViewBinding> : BaseVmFragment<VM>() {
    override fun layoutId(): Int = 0
    private var binding: VB? = null
    val mViewBind: VB get() = binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflateBinding(inflater, container, false)
        return mViewBind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}