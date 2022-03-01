package com.example.jetpackmvvm.ext

import java.lang.reflect.ParameterizedType

/**
 * 获取当前绑定类的泛型ViewModel-clazz
 */
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

