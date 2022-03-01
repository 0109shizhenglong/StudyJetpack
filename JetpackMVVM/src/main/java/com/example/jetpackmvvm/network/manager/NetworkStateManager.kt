package com.example.jetpackmvvm.network.manager

import com.example.jetpackmvvm.callback.livedata.event.EventLiveData

class NetworkStateManager private constructor() {
    val mNetwork = EventLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }
}