package com.example.studyjetpack.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.preference.PreferenceManager
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.Utils
import com.example.studyjetpack.R
import com.example.studyjetpack.weight.loadCallBack.LoadingCallback
import com.kingja.loadsir.core.LoadService
import com.tencent.mmkv.MMKV

object SettingUtil {
    /**
     * 获取当前主题色
     */
    fun getColor(context: Context): Int {
        val setting = PreferenceManager.getDefaultSharedPreferences(context)
        val defaultColor = ContextCompat.getColor(context, R.color.colorPrimary)
        val color = setting.getInt("color", defaultColor)
        return if (color != 0 && Color.alpha(color) != 255) {
            defaultColor
        } else {
            color
        }
    }

    /**
     * 设置主题颜色
     */
    fun setColor(context: Context, color: Int) {
        val setting = PreferenceManager.getDefaultSharedPreferences(context)
        setting.edit().putInt("color", color).apply()
    }

    /**
     * 获取列表动画模式
     */
    fun getListMode(): Int {
        val kv = MMKV.mmkvWithID("app")
        //0 关闭动画 1.渐显 2 缩放 3.从下到上 4从左到右 5 从右到左
        return kv.decodeInt("mode", 2)
    }

    /**
     * 设置列表动画模式
     */
    fun setListMode(mode: Int) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("mode", mode)
    }

    fun getColorStateList(color: Int): ColorStateList {
        val colors = intArrayOf(color, ContextCompat.getColor(Utils.getApp(), R.color.colorGray))
        val states = arrayOfNulls<IntArray>(2)
        states[0] = intArrayOf(android.R.attr.state_checked, android.R.attr.state_checked)
        states[1] = intArrayOf()
        return ColorStateList(states, colors)
    }


    fun getOneColorStateList(context: Context): ColorStateList {
        val colors = intArrayOf(getColor(context))
        val states = arrayOfNulls<IntArray>(1)
        states[0] = intArrayOf()
        return ColorStateList(states, colors)
    }

    fun getOneColorStateList(color: Int): ColorStateList {
        val colors = intArrayOf(color)
        val states = arrayOfNulls<IntArray>(1)
        states[0] = intArrayOf()
        return ColorStateList(states, colors)
    }

    /**
     * 设置shape文件的颜色
     */
    fun setShapeColor(view: View, color: Int) {
        val drawable = view.background as GradientDrawable
        drawable.setColor(color)
    }

    /**
     * 设置shape的渐变颜色
     */
    fun setShapeGradientColor(view: View, color: IntArray, orientation: GradientDrawable.Orientation) {
        val drawable = view.background as GradientDrawable
        drawable.orientation = orientation//渐变方向
        drawable.colors = color;//渐变颜色数组
    }

    /**
     * 设置loading的颜色加载布局
     */
    fun setLoadingColor(color: Int, loadSir: LoadService<Any>) {
        loadSir.setCallBack(LoadingCallback::class.java) { _, view ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.findViewById<ProgressBar>(R.id.loading_progress).indeterminateTintMode =
                    PorterDuff.Mode.SRC_ATOP
                view.findViewById<ProgressBar>(R.id.loading_progress).indeterminateTintList =
                    getOneColorStateList(color)
            }
        }
    }
}