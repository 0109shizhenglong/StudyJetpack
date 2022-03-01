package com.example.jetpackmvvm.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.example.jetpackmvvm.base.viewmodel.BaseViewModel
import com.example.jetpackmvvm.ext.inflateBinding

abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmFragment<VM>() {
    override fun layoutId(): Int = 0

    private var binding: DB? = null
    val mDataBind: DB get() = binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflateBinding(inflater, container, false)
        return mDataBind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}