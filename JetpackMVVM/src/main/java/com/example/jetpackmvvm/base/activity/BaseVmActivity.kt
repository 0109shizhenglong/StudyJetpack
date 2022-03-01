package com.example.jetpackmvvm.base.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackmvvm.base.viewmodel.BaseViewModel
import com.example.jetpackmvvm.ext.getVmClazz
import com.example.jetpackmvvm.ext.util.notNull
import com.example.jetpackmvvm.network.manager.NetState
import com.example.jetpackmvvm.network.manager.NetworkStateManager

/**
 * ViewModelActivity基类，把ViewModel注入进来
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {
    lateinit var mViewModel: VM

    abstract fun layoutId(): Int
    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun showLoading(message: String = "请求网路中")

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBind().notNull({
            setContentView(it)
        }, {
            setContentView(layoutId())
        })
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        registerUiChange()
        initView(savedInstanceState)
        createObserver()
        NetworkStateManager.instance.mNetwork.observeInActivity(this) {
            onNetworkStateChanged(it)
        }
    }

    /**
     * 网络变化监听，子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 创建ViewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    /**
     * 注册UI事件
     */
    private fun registerUiChange() {
        //显示弹窗
        mViewModel.loadingChange.showDialog.observeInActivity(this) {
            showLoading(it)
        }
        //关闭弹窗
        mViewModel.loadingChange.dismissDialog.observeInActivity(this) {
            dismissLoading()
        }
    }

    /**
     * 将非该Activity绑定的ViewModel添加loading回调，防止出现请求时不显示loading 弹窗bug
     */
    protected fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewmodel ->
            //显示弹窗
            viewmodel.loadingChange.showDialog.observeInActivity(this) {
                showLoading(it)
            }
            //关闭弹窗
            viewmodel.loadingChange.dismissDialog.observeInActivity(this) {
                dismissLoading()
            }
        }
    }

    /**
     * 供子类BaseVmDbActivity 初始化DataBinding操作
     */
    open fun initDataBind(): View? {
        return null
    }
}