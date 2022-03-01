package com.example.jetpackmvvm.base.activity

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.example.jetpackmvvm.base.viewmodel.BaseViewModel
import com.example.jetpackmvvm.ext.inflateBinding


abstract class BaseVmVbActivity<VM : BaseViewModel, DB : ViewBinding> : BaseVmActivity<VM>() {
    override fun layoutId(): Int = 0

    lateinit var mViewBind: DB
    override fun initDataBind(): View? {
        mViewBind = inflateBinding(layoutInflater)
        return mViewBind.root
    }


}