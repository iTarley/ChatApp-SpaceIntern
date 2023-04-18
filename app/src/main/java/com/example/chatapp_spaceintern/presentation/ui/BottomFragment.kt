package com.example.chatapp_spaceintern.presentation.ui

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.chatapp_spaceintern.R
import com.example.chatapp_spaceintern.databinding.FragmentBottomBinding
import com.example.chatapp_spaceintern.domain.model.MessageModel
import com.example.chatapp_spaceintern.presentation.adapter.RecyclerAdapter
import com.example.chatapp_spaceintern.presentation.base.BaseFragment
import com.example.chatapp_spaceintern.presentation.base.Inflate
import com.example.chatapp_spaceintern.utils.extension.currentTime
import com.example.chatapp_spaceintern.utils.extension.isNetworkAvailable
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class BottomFragment : BaseFragment<FragmentBottomBinding>() {

    private val viewModel by viewModel<SharedViewModel>()

    private val adapter by lazy {
        RecyclerAdapter(SENDER)
    }

    override fun inflate(): Inflate<FragmentBottomBinding> {
        return FragmentBottomBinding::inflate
    }

    override fun onBind() {
        initRecycler()
        binding.imageBtnView.setOnClickListener {
            saveMessageModel()
        }
    }

    override fun saveMessageModel() {
        if (isNetworkAvailable(context)) {
            sendMessage(
                MessageModel(
                    id = null,
                    sender = SENDER,
                    message = binding.inputEditText.text.toString(),
                    time = currentTime()
                )
            )
        } else {
            Toast.makeText(context, getString(R.string.check_internet), Toast.LENGTH_SHORT).show()
        }

        binding.inputEditText.text?.clear()
    }

    override fun sendMessage(messageModel: MessageModel) {
        viewModel.sendMessage(messageModel)
    }

    override fun initRecycler() {
        binding.bottomRecycler.adapter = adapter
        showMessages()
    }

    override fun showMessages() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.showMessages().collect {
                adapter.submitList(it)
            }
        }
    }

    companion object {
        private const val SENDER = "BOTTOM"
    }
}