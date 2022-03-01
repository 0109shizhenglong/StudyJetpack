package com.example.studyjetpack.weight.loadCallBack

import com.example.studyjetpack.R
import com.kingja.loadsir.callback.Callback


class ErrorCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}