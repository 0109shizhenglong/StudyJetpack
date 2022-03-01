package com.example.studyjetpack.ext

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackmvvm.base.appContext
import com.example.studyjetpack.R
import com.example.studyjetpack.util.SettingUtil
import com.example.studyjetpack.weight.loadCallBack.ErrorCallback
import com.example.studyjetpack.weight.loadCallBack.LoadingCallback
import com.example.studyjetpack.weight.recyclerview.DefineLoadMoreView
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.yanzhenjie.recyclerview.SwipeRecyclerView


fun LoadService<*>.setErrorText(message: String) {
    if (message.isNotEmpty()) {
        this.setCallBack(ErrorCallback::class.java) { _, view ->
            view.findViewById<TextView>(R.id.error_text).text = message
        }
    }
}

/**
 * 设置错误布局
 */
fun LoadService<*>.showError(message: String = "") {
    this.setErrorText(message)
    this.showCallback(ErrorCallback::class.java)
}

/**
 * 设置空布局
 */
fun LoadService<*>.showEmpty() {
    this.showCallback(EmptyCallback::class.java)
}

/**
 * 设置加载中
 */
fun LoadService<*>.showLoading() {
    this.showCallback(LoadingCallback::class.java)
}

fun loadServiceInit(view: View, callback: () -> Unit): LoadService<Any> {
    val loadSir = LoadSir.getDefault().register(view) {
        //点击重试时触发的操作
        callback.invoke()
    }
    loadSir.showSuccess()
    SettingUtil.setLoadingColor(SettingUtil.getColor(appContext), loadSir)
    return loadSir
}

//绑定普通的Recyclerview
fun RecyclerView.init(
    layoutManager: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
    isScroll: Boolean = true
): RecyclerView {
    this.layoutManager = layoutManager
    setHasFixedSize(true)
    adapter = bindAdapter
    isNestedScrollingEnabled = isScroll
    return this
}

//绑定SwipeRecyclerView
fun SwipeRecyclerView.init(
    layoutManager: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
    isScroll: Boolean = true
): SwipeRecyclerView {
    this.layoutManager = layoutManager
    setHasFixedSize(true)
    isNestedScrollingEnabled = isScroll
    return this
}

fun SwipeRecyclerView.initFooter(loadmoreListener: SwipeRecyclerView.LoadMoreListener): DefineLoadMoreView {
    val footerView = DefineLoadMoreView(appContext)
    //给尾部设置颜色
    footerView.setLoadViewColor(SettingUtil.getOneColorStateList(appContext))
    //设置尾部点击回调
    footerView.setLoadMoreListener(SwipeRecyclerView.LoadMoreListener {
        footerView.onLoading()
        loadmoreListener.onLoadMore()
    })
    this.run {
        //添加加载更多尾部
        addFooterView(footerView)
        setLoadMoreView(footerView)
        //设置加载更多回调
        setLoadMoreListener(loadmoreListener)
    }
    return footerView
}
