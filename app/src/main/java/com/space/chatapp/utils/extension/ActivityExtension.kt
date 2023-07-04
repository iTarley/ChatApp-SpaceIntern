package com.space.chatapp.utils.extension

import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView



/** This extension function is used to set fragment to container view
 * @param container is the container view
 * @param fragment is the fragment to be set
 * */
fun AppCompatActivity.setFragmentToContainer(container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {
        if (savedStateRegistry.isRestored) {
            replace(container, fragment)
        }else{
            add(container, fragment)
        }
    }.commit()
}