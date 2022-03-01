package com.example.jetpackmvvm.base.activity

import android.view.View
import androidx.databinding.ViewDataBinding
import com.example.jetpackmvvm.base.viewmodel.BaseViewModel
import com.example.jetpackmvvm.ext.inflateBinding

abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>() {
    override fun layoutId(): Int = 0

    lateinit var mDataBind: DB
    override fun initDataBind(): View? {
        mDataBind = inflateBinding(layoutInflater)
        return mDataBind.root
    }
}