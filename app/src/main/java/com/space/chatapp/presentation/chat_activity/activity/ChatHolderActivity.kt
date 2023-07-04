package com.space.chatapp.presentation.chat_activity.activity

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.space.chatapp.R
import com.space.chatapp.databinding.ActivityMainBinding
import com.space.chatapp.presentation.chat_activity.viewmodel.ChatHolderViewModel
import com.space.chatapp.presentation.chat_screen.ui.fragment.ChatFragment
import com.space.chatapp.utils.ChatThemeMode
import com.space.chatapp.utils.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * This activity is used to hold the fragments and maintain the theme state
 */

class ChatHolderActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModel<ChatHolderViewModel>()
    private val containers = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        hideNavBar()
        if (savedInstanceState != null) {
            val savedContainerIds = savedInstanceState.getIntegerArrayList(CONTAINERS_STATE_KEY)
            if (savedContainerIds != null) {
                containers.addAll(savedContainerIds)
            }
        }
        initFragment(2)
        observeThemeMode()
        checkPreferencesStatus()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val containerIds = ArrayList<Int>(containers)
        outState.putIntegerArrayList(CONTAINERS_STATE_KEY, containerIds)
    }

    private fun checkPreferencesStatus() {
        launchWithLifecycle {
            viewModel.checkPreferencesStatus()
        }
    }

    private fun observeThemeMode() {
        launchWithLifecycle {
            viewModel.state.collect { mode ->
                val resourceId = when (mode) {
                    ChatThemeMode.DAY_MODE -> R.drawable.day_night_switch
                    ChatThemeMode.NIGHT_MODE -> R.drawable.night_day_switch
                }
                with(binding) {
                    dayNightSwitcher?.getDrawable(binding.root.context, resourceId)
                    dayNightSwitcher?.setOnClickListener {
                        viewModel.themeUpdate()
                    }
                }
            }
        }
    }

    private fun hideNavBar() {
        supportActionBar?.hide()
    }

    /**
     * Initialize fragment container
     */
    private fun initFragment(containerCount: Int) {
        with(binding) {
            containers.clear()
            val totalWeight = containerCount.toFloat()

            for (i in 0 until containerCount) {
                val frameLayout = createFrameLayout()
                containers.add(frameLayout.id)

                val layoutParams = createLayoutParams(totalWeight, binding.root.context)
                fragmentContainer.addView(frameLayout, layoutParams)

                val divider = createDivider()
                val dividerLayoutParams = createDividerLayoutParams(binding.root.context)
                fragmentContainer.addView(divider, dividerLayoutParams)
            }
            containers.forEachIndexed { index, frameLayout ->
                val fragment by lazy { createChatFragment(index) }
                val bundle by lazy { createBundle(index) }
                fragment.arguments = bundle
                setFragmentToContainer(frameLayout, fragment)
            }
        }
    }

    private fun createFrameLayout(): FrameLayout {
        val frameLayout = FrameLayout(binding.root.context)
        frameLayout.id = View.generateViewId()
        return frameLayout
    }

    private fun createChatFragment(index: Int): ChatFragment {
        val fragment by lazy { ChatFragment() }
        val bundle by lazy { createBundle(index) }
        fragment.arguments = bundle
        return fragment
    }

    private fun createDivider(): View {
        val divider = View(binding.root.context)
        divider.setBackgroundColorRes(R.color.dark_yellow200)
        return divider
    }

    /**
     * Create layout params for fragment container
     */
    private fun createLayoutParams(
        totalWeight: Float,
        context: Context
    ): LinearLayout.LayoutParams {
        val orientation = context.resources.configuration.orientation
        val layoutParams: LinearLayout.LayoutParams =
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Landscape mode parameters
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1f / totalWeight
                )
            } else {
                // Portrait or other mode parameters
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1f / totalWeight
                )
            }
        return layoutParams
    }

    /**
     * Create divider layout params
     */
    private fun createDividerLayoutParams(context: Context): LinearLayout.LayoutParams {
        val orientation = context.resources.configuration.orientation
        val layoutParams: LinearLayout.LayoutParams =
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Landscape mode parameters
                LinearLayout.LayoutParams(
                    6,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            } else {
                // Portrait or other mode parameters
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    6
                )
            }
        return layoutParams
    }

    /**
     * Create bundle for fragment
     */
    private fun createBundle(index: Int): Bundle {
        val bundle = Bundle()
        bundle.putString(USER_ID, "fragment_${index + 1}")
        return bundle
    }

    companion object {
        private const val CONTAINERS_STATE_KEY = "containers_state_key"
        const val USER_ID = "userId"
    }
}