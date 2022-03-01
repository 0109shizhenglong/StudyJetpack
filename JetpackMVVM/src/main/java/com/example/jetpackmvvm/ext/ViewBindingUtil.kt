package com.example.jetpackmvvm.ext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType


@JvmName("inflateWithGeneric")
fun <VB : ViewBinding> AppCompatActivity.inflateBinding(layoutInflater: LayoutInflater): VB =
    withGenericBindingClass<VB>(this) { clazz ->
        clazz.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB
    }.also { binding ->
        if (binding is ViewDataBinding) {
            binding.lifecycleOwner = this
        }
    }

@JvmName("inflateWithGeneric")
fun <VB : ViewBinding> Fragment.inflateBinding(layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): VB =
    withGenericBindingClass<VB>(this) { clazz ->
        clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            .invoke(null, layoutInflater, parent, attachToParent) as VB
    }.also { binding ->
        if (binding is ViewDataBinding) {
            binding.lifecycleOwner = viewLifecycleOwner
        }
    }

private fun <VB : ViewBinding> withGenericBindingClass(any: Any, block: (Class<VB>) -> VB): VB {
    var genericSuperClass = any.javaClass.genericSuperclass
    var superClass = any.javaClass.superclass
    while (superClass != null) {
        if (genericSuperClass is ParameterizedType) {
            try {
                return block.invoke(genericSuperClass.actualTypeArguments[1] as Class<VB>)
            } catch (e: Exception) {
            }
        }
        genericSuperClass = superClass.genericSuperclass
        superClass = superClass.superclass
    }
    throw IllegalArgumentException("There is no generic of ViewBinding.")
}