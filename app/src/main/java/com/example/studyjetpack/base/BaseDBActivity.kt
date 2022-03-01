package com.example.studyjetpack.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.example.jetpackmvvm.base.activity.BaseVmDbActivity
import com.example.jetpackmvvm.base.viewmodel.BaseViewModel
import com.example.studyjetpack.ext.dismissLoadingExt
import com.example.studyjetpack.ext.showLoadingExt

abstract class BaseDBActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    abstract override fun initView(savedInstanceState: Bundle?)

    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    override fun dismissLoading() {
        dismissLoadingExt()
    }

    override fun createObserver() {
    }

//在任何情况下本来适配正常的布局突然出现适配失效，适配异常等问题，只要重写 Activity 的 getResources() 方法
    /*
    override fun getResources(): Resources {
        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
        return super.getResources()
    }*/
}