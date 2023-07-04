package com.space.chatapp.presentation.chat_screen.ui.chat_style_strategy

import android.view.View
import com.space.chatapp.R
import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.presentation.model.Message
import com.space.chatapp.utils.extension.setImageTint
import com.space.chatapp.utils.extension.setTextColorCompat
import com.space.chatapp.utils.extension.setTint

/**
 * Strategy for setting ui elements
 */

private fun setBubbleColor(
    binding: ChatMessageViewBinding,
    item: Message,
    color: Int
) {
    with(binding) {
        dateTextViewTo.text = item.time.toString()
        dateTextViewTo.setTextColorCompat(R.color.grey_200)
        rectangleBigBubble.setImageTint(color)
        sendToTextView.setTint(color)
        rectangleSmallBubble.setImageTint(color)
    }
}

class SentMessageUIStrategy : MessageUIStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: Message) {
        setBubbleColor(binding, item, R.color.purple_light)
        with(binding) {
            sendToTextView.setTextColorCompat(R.color.eerie_black)
            root.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }
    }
}

class SentNoInternetMessageUIStrategy : MessageUIStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: Message) {
        setBubbleColor(binding, item, R.color.purple_light)
        with(binding) {
            dateTextViewTo.text = dateTextViewTo.context.getString(R.string.not_delivered)
            sendToTextView.setTextColorCompat(R.color.fade)
            dateTextViewTo.setTextColorCompat(R.color.red_error)
            root.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }
    }
}

class ReceivedMessageUIStrategy : MessageUIStrategy {
    override fun setUiElements(binding: ChatMessageViewBinding, item: Message) {
        setBubbleColor(binding, item, R.color.darker_white)
        with(binding) {
            sendToTextView.setTextColorCompat(R.color.eerie_black)
            root.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }
}
