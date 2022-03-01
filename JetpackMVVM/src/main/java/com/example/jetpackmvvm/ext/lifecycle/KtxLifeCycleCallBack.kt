package com.example.jetpackmvvm.ext.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.jetpackmvvm.ext.util.logD


class KtxLifeCycleCallBack : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        KtxActivityManger.pushActivity(activity)
        "onActivityCreated : ${activity.localClassName}".logD()
    }

    override fun onActivityStarted(activity: Activity) {
        "onActivityStarted : ${activity.localClassName}".logD()
    }

    override fun onActivityResumed(activity: Activity) {
        "onActivityResumed : ${activity.localClassName}".logD()
    }

    override fun onActivityPaused(activity: Activity) {
        "onActivityPaused : ${activity.localClassName}".logD()
    }


    override fun onActivityDestroyed(activity: Activity) {
        "onActivityDestroyed : ${activity.localClassName}".logD()
        KtxActivityManger.popActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityStopped(activity: Activity) {
        "onActivityStopped : ${activity.localClassName}".logD()
    }


}