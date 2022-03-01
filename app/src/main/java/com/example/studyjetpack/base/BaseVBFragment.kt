package com.example.studyjetpack.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.example.jetpackmvvm.base.fragment.BaseVmDbFragment
import com.example.jetpackmvvm.base.viewmodel.BaseViewModel
import com.example.studyjetpack.ext.dismissLoadingExt

abstract class BaseVBFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {
    abstract override fun initView(savedInstanceState: Bundle?)


    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {
    }

    /**
     * 创建LiveData观察者  Fragment执行onViewCreated后触发
     */
    override fun createObserver() {
    }

    /**
     * Fragment执行onViewCreated后触发
     */
    override fun initData() {

    }

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {

    }


    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }

    override fun onPause() {
        super.onPause()
        hides
    }

    /**
     * 延迟加载 防止切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿bug这里传入你想要的延迟时间
     * 延迟时间可以比转场动画时间长一点 单位：毫秒
     * 不传默认 300毫秒
     */
    override fun lazyLoadTime(): Long {
        return 300
    }
}