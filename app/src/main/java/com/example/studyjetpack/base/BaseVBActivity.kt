package com.example.studyjetpack.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.jetpackmvvm.base.activity.BaseVmVbActivity
import com.example.jetpackmvvm.base.viewmodel.BaseViewModel
import com.example.studyjetpack.ext.dismissLoadingExt
import com.example.studyjetpack.ext.showLoadingExt

abstract class BaseVBActivity<VM : BaseViewModel, VB : ViewBinding> : BaseVmVbActivity<VM, VB>() {

    abstract override fun initView(savedInstanceState: Bundle?)

    /**
     * 创建liveData观察者
     */
    override fun createObserver() {}

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }

    /* */
    /**
     * 在任何情况下本来适配正常的布局突然出现适配失效，适配异常等问题，只要重写 Activity 的 getResources() 方法
     *//*
    override fun getResources(): Resources {
        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
        return super.getResources()
    }*/
}